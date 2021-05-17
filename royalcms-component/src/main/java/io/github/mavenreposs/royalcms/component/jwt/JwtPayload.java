package io.github.mavenreposs.royalcms.component.jwt;

public class JwtPayload {

    /**
     * 该JWT的签发者，是否使用是可选的
     */
    protected String iss;

    /**
     * expires 什么时候过期，这里是一个Unix时间戳，是否使用是可选的
     */
    protected Integer exp;

    /**
     * 接收该JWT的一方，是否使用是可选的
     */
    protected String aud;

    /**
     * 该JWT所面向的用户，是否使用是可选的
     */
    protected String sub;

    /**
     * issued at 在什么时候签发的(UNIX时间)，是否使用是可选的
     */
    protected String iat;

    /**
     * Not Before 如果当前时间在nbf里的时间之前，则Token不被接受；一般都会留一些余地，比如几分钟，是否使用是可选的
     */
    protected Integer nbf;

    /**
     * jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     */
    protected String jti;


    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public Integer getNbf() {
        return nbf;
    }

    public void setNbf(Integer nbf) {
        this.nbf = nbf;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }


}
