package cn.royalcms.facades.jwt;

import cn.royalcms.facades.log.RC_Log;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Test_Jwt {

    @Test
    public void test_encode() {
        String secretKey = "131cf1eab84d336baae739097b0f77f0";
        String a = RC_Jwt.encode(Map.of("user_id", 13724), secretKey);
        RC_Log.info(a);
    }

    @Test
    public void test_decode() {
        // PHP 签名出来的token
        String secretKey = "131cf1eab84d336baae739097b0f77f0";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wveC5kc2NtYWxsLmNuIiwiZXhwIjoxNjIxOTI3MjU2LCJib2R5Ijp7InVzZXJfaWQiOjF9fQ.lrYVXTTl359Pw008yoB6f_Th3DWgJ9E2oG_F3wJkWO0";

        Map<String, Object> a = RC_Jwt.decode(token, secretKey);

        RC_Log.object(a);

    }

    @Test
    public void test_decode01() {
        // java 签名出来的token
        String secretKey = "131cf1eab84d336baae739097b0f77f0";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJib2R5Ijp7InVzZXJfaWQiOjExfSwiaXNzIjoiaHR0cHM6Ly94LmRzY21hbGwuY24iLCJleHAiOjE2MjE5Mjg4MDZ9.XjhSMoDnMjOGqzy-KNOhH9_NLpdy6roEiFPn1x8Etl4";

        Map<String, Object> a = RC_Jwt.decode(token, secretKey);

        RC_Log.object(a);
    }





}
