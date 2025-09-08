package com.oocl.coldplayfans.controller;

import com.oocl.coldplayfans.dao.User;
import com.oocl.coldplayfans.service.UserService;
import com.oocl.coldplayfans.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public Map<String, Object> login(@RequestBody User user){
        // Todo: 存储密码为加密串
        Map<String, Object> map = new HashMap<>();
        try{
            User login = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(login.getId()));
            payload.put("name", login.getUsername());
            payload.put("password", login.getPassword());
            String token = JwtUtil.getToken(payload);

            map.put("status", true);
            map.put("msg", "登录成功");
            map.put("token", token); // 响应token，存储在客户端
        }catch (Exception e) {
            map.put("status", false);
            map.put("msg", "登录失败");
        }
        return map;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        // Todo:返回不带密码字段，UserResponseDTO
        return userService.register(user);
    }
}
