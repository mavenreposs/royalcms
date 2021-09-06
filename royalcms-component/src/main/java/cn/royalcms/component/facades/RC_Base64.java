package cn.royalcms.component.facades;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;

public class RC_Base64 {

    private RC_Base64() {
    }

    /**
     * Base64 编码
     * @param key 普通字符串
     * @return base64字符串
     */
    public static String base64_encode(String key) {
        String base64EncodedSecretKey = Encoders.BASE64.encode(key.getBytes());
        return base64EncodedSecretKey;
    }

    /**
     * Base64 解码
     * @param base64EncodedSecretKey  base64字符串
     * @return 普通字符串
     */
    public static String base64_decode(String base64EncodedSecretKey) {
        byte[] key = Decoders.BASE64.decode(base64EncodedSecretKey);
        return key.toString();
    }

    /**
     * Base64 url兼容 编码
     * @param key 普通字符串
     * @return base64字符串
     */
    public static String base64url_encode(String key) {
        String base64EncodedSecretKey = Encoders.BASE64URL.encode(key.getBytes());
        return base64EncodedSecretKey;
    }

    /**
     * Base64 url兼容 编码
     * @param base64EncodedSecretKey
     * @return 普通字符串
     */
    public static String base64url_decode(String base64EncodedSecretKey) {
        byte[] key = Decoders.BASE64URL.decode(base64EncodedSecretKey);
        return key.toString();
    }

}
