package cn.royalcms.component.jwt.facades;

import cn.royalcms.facades.base64.RC_Base64;
import cn.royalcms.facades.time.RC_Time;
import cn.royalcms.component.jwt.JwtPayload;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class RC_Jwt {
    private RC_Jwt() {
    }

    public static Key createKey(String secretKey) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return key;
    }

    public static Key createBase64Key(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return key;
    }

    /**
     * 加密
     * @param data Map
     * @param secretKey String
     * @return string
     */
    public static String encode(Map<String, Object> data, String secretKey) {
        JwtPayload payload = new JwtPayload();
        payload.setExp(RC_Time.time() + 3600*24*30);

        return encode(data, secretKey, payload);
    }

    /**
     * 加密
     * @param data Map
     * @param secretKey String
     * @param jwtPayload JwtPayload
     * @return string
     */
    public static String encode(Map<String, Object> data, String secretKey, JwtPayload jwtPayload) {
        Key key = createKey(secretKey);

        JwtBuilder builder = Jwts.builder().claim("body", data);

        builder.signWith(key, SignatureAlgorithm.HS256);

        if (jwtPayload.getIss() != null) {
            builder.setIssuer(jwtPayload.getIss());
        }

        if (jwtPayload.getAud() != null) {
            builder.setAudience(jwtPayload.getAud());
        }

        if (jwtPayload.getExp() != 0) {
            int time = jwtPayload.getExp();
            builder.setExpiration(Date.from(Instant.ofEpochSecond(time)));
        }

        if (jwtPayload.getSub() != null) {
            builder.setSubject(jwtPayload.getSub());
        }

        if (jwtPayload.getNbf() != null) {
            int time = RC_Time.time() + jwtPayload.getNbf() * 60;
            builder.setNotBefore(Date.from(Instant.ofEpochSecond(time)));
        }

        String jws = builder.compact();

        return jws;
    }

    /**
     * 加密
     * @param data Map
     * @param base64EncodedSecretKey String
     * @return string
     */
    public static String encodeBase64(Map<String, Object> data, String base64EncodedSecretKey) {
        JwtPayload payload = new JwtPayload();
        payload.setExp(RC_Time.time() + 3600*24*30);

        return encode(data, base64EncodedSecretKey, payload);
    }

    /**
     * 加密
     * @param data Map
     * @param base64EncodedSecretKey String
     * @param jwtPayload JwtPayload
     * @return string
     */
    public static String encodeBase64(Map<String, Object> data, String base64EncodedSecretKey, JwtPayload jwtPayload) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        JwtBuilder builder = Jwts.builder().claim("body", data);

        builder.signWith(key, SignatureAlgorithm.HS256);

        if (jwtPayload.getIss() != null) {
            builder.setIssuer(jwtPayload.getIss());
        }

        if (jwtPayload.getAud() != null) {
            builder.setAudience(jwtPayload.getAud());
        }

        if (jwtPayload.getExp() != 0) {
            int time = jwtPayload.getExp();
            builder.setExpiration(Date.from(Instant.ofEpochSecond(time)));
        }

        if (jwtPayload.getSub() != null) {
            builder.setSubject(jwtPayload.getSub());
        }

        if (jwtPayload.getNbf() != null) {
            int time = RC_Time.time() + jwtPayload.getNbf() * 60;
            builder.setNotBefore(Date.from(Instant.ofEpochSecond(time)));
        }

        String jws = builder.compact();

        return jws;
    }

    /**
     * 解密
     * @param token String
     * @param secretKey String
     * @return Map
     */
    public static Map<String, Object> decode(String token, String secretKey) {
        String base64EncodedSecretKey = RC_Base64.base64_encode(secretKey);
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(base64EncodedSecretKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * 解密
     * @param token String
     * @param base64EncodedSecretKey String
     * @return Map
     */
    public static Map<String, Object> decodeBase64(String token, String base64EncodedSecretKey) {
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(base64EncodedSecretKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

}
