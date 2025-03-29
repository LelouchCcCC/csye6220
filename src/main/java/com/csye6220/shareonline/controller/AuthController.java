package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.dto.UserInfo;
import com.csye6220.shareonline.model.User;
import com.csye6220.shareonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserInfo userInfo) {
        return userService.registerUser(userInfo.getUsername(), userInfo.getEmail(), userInfo.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo) {
        return userService.loginUser(userInfo.getEmail(), userInfo.getPassword());
    }
}
