package cn.royalcms.component.jwt;

import cn.royalcms.component.facades.jwt.RC_Jwt;
import cn.royalcms.component.facades.log.RC_Log;
import cn.royalcms.component.facades.log.RC_Logger;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUserToken {

    private static final String CLAIM_KEY_BODY = "body";

    /**
     * 加密秘钥
     */
    private String secret = "null";

    /**
     * 设置有效时间，为30天
     */
    private long expiration = (long) 3600*24*30 * 1000;

    private final Key key;

    public JwtUserToken(String secret, long expiration) {
        this.secret = secret;
        int day = 3600*24*30;
        this.expiration = expiration * day;
        this.key = RC_Jwt.createKey(this.secret);
    }

    /**
     * 初始化生成token的参数
     * @param userId 用户ID
     * @return String
     */
    public String generateToken(int userId) {
        JwtBody body = new JwtBody();
        body.setUserId(userId);
        return generateToken(body);
    }

    /**
     * 生成token
     * @param body JWT加密消息体
     * @return String
     */
    private String generateToken(JwtBody body) {
        return Jwts.builder()
                .claim(CLAIM_KEY_BODY, body)
                .setExpiration(this.generateExpirationDate())
                .setIssuedAt(this.generateCurrentDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 生成token
     * @param claims JWT标准消息体
     * @return String
     */
    private String generateToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .setIssuedAt(this.generateCurrentDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }


    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    /**
     * 判断token是否可以刷新
     * @param token jwt token
     * @param lastPasswordReset 最后修改日期
     * @return Boolean
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            final Date iat = claims.getIssuedAt();
            final Date exp = claims.getExpiration();
            if (iat.before(lastPasswordReset) || exp.before(generateCurrentDate())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新token
     * @param token jwt token
     * @return token
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 校验token
     * @param token jwt token
     * @return JwtTokenStatus
     */
    public JwtTokenStatus verifyToken(String token) {
        JwtTokenStatus result = null;

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            result = JwtTokenStatus.TOKEN_VALID;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            RC_Logger.info("Invalid JWT signature.");
            RC_Logger.trace("Invalid JWT signature trace: {}", e);
            result = JwtTokenStatus.TOKEN_INVALID;
        } catch (ExpiredJwtException e) {
            RC_Logger.info("Expired JWT token.");
            RC_Logger.trace("Expired JWT token trace: {}", e);
            result = JwtTokenStatus.TOKEN_EXPIRED;
        } catch (UnsupportedJwtException e) {
            RC_Logger.info("Unsupported JWT token.");
            RC_Logger.trace("Unsupported JWT token trace: {}", e);
            result = JwtTokenStatus.TOKEN_INVALID;
        } catch (IllegalArgumentException e) {
            RC_Logger.info("JWT token compact of handler are invalid.");
            RC_Logger.trace("JWT token compact of handler are invalid trace: {}", e);
            result = JwtTokenStatus.TOKEN_INVALID;
        } catch (Exception e) {
            result = JwtTokenStatus.TOKEN_INVALID;
        }
        return result;
    }

    /**
     * 获取解析TOKEN原始数据体
     * @param token jwt token
     * @return Claims
     */
    public Claims getParseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            RC_Log.object(e);
            return new DefaultClaims(Map.of());
        }
    }

    /**
     * 获取JwtBody数据体
     * @param token jwt token
     * @return Claims
     */
    @SuppressWarnings("unchecked")
    public JwtBody getJwtBodyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return new JwtBody((Map<String, Object>) claims.get(CLAIM_KEY_BODY));
        } catch (Exception e) {
            RC_Log.object(e);
            return new JwtBody(Map.of());
        }
    }

    /**
     * 获取用户编号
     * @param token jwt token
     */
    @SuppressWarnings("unchecked")
    public Integer getUserIdFromToken(String token) {
        int userId;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            JwtBody body = new JwtBody((Map<String, Object>) claims.get(CLAIM_KEY_BODY));
            return body.getUserId();
        } catch (Exception e) {
            userId = 0;
        }
        return userId;
    }


}
