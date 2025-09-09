package com.oocl.coldplayfans.controller;

import com.oocl.coldplayfans.dao.User;
import com.oocl.coldplayfans.dto.UserResponse;
import com.oocl.coldplayfans.dto.mapper.UserMapper;
import com.oocl.coldplayfans.service.UserService;
import com.oocl.coldplayfans.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
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
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserResponse userResponse = new UserMapper().toResponse(userService.register(user));
            map.put("userReponse", userResponse);
            map.put("msg", "注册成功");
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
        }
    }

}
