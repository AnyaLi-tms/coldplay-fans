package com.oocl.coldplayfans.integration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oocl.coldplayfans.repository.UserDbRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private UserDbRepository userDbRepository;

    @Autowired
    private MockMvc client;

    @Test
    public void should_register_user_successfully(){

    }

    @Test
    public void should_return_token_when_login_user_successfully(){

    }

    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 2000);
        String token = JWT.create().withHeader(map) //header
                .withClaim("userId", 21)//payload
                .withClaim("username", "zuhao")//payload
                .withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256("!Q@W#E$R")) //签名
                ;
        System.out.println(token);
    }


    @Test
    void test() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsIm5hbWUiOiJ0ZXN0MTIzIiwiaWQiOiIxIiwiZXhwIjoxNzU3MzgyNDMwfQ.9F0hyZYDUExotmF1uu_tJkRLe7__P1m9mXVQZ_hM8qo";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q@W#E$R")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println("用户Id：" + decodedJWT.getClaim("id").asString());
        System.out.println("用户名：" + decodedJWT.getClaim("name").asString());
        System.out.println("过期时间：" + decodedJWT.getExpiresAt());
    }
}
