package com.oocl.coldplayfans.controller;

import com.oocl.coldplayfans.dao.User;
import com.oocl.coldplayfans.dto.UserResponse;
import com.oocl.coldplayfans.dto.mapper.UserMapper;
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
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            User login = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(login.getId()));
            payload.put("name", login.getUsername());
            payload.put("password", login.getPassword());
            String token = JwtUtil.getToken(payload);
            map.put("status", true);
            map.put("msg", "登录成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "登录失败");
        }
        return map;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        return new UserMapper().toResponse(userService.register(user));
    }
}
