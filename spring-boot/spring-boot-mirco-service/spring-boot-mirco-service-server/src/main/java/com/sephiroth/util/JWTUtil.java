package com.sephiroth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static String sign(String u){
        Date experts = DateUtils.addMinutes(new Date(),1);
        Map map = new HashMap<>();
        map.put("username",u);
        String token = JWT.create()
                .withClaim("ss123",new Date())
                .withExpiresAt(experts)
                .withPayload(map)
                .sign(Algorithm.HMAC256("ss123"));
        return token;
    }

    public static boolean verify(String token){
        try{
            DecodedJWT ss123 = JWT.require(Algorithm.HMAC256("ss123")).build().verify(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String test = sign("test");
        System.out.println(test);
        boolean verify = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzczEyMyI6MTYzNDg4MDkyNSwiZXhwIjoxNjM0ODgwOTg0LCJ1c2VybmFtZSI6InRlc3QifQ.xePEPGvkhGKNpXGdP04hhdXIPL1dn-eu39VpKQQZpFY");
        System.out.println(verify);
    }
}
