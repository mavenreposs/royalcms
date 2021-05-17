package io.github.mavenreposs.royalcms.jwt;

/**
 * token状态
 */
public enum JwtTokenStatus {

    TOKEN_EXPIRED("EXPIRED"), // 过期
    TOKEN_INVALID("INVALID"), // 无效
    TOKEN_VALID("VALID"), // 有效
    ;

    private final String value;

    JwtTokenStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
