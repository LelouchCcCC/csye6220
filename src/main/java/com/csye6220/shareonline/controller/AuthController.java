package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.dto.UserInfo;
import com.csye6220.shareonline.model.User;
import com.csye6220.shareonline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService svc) { this.userService = svc; }

    /** register */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserInfo dto) {
        User u = userService.registerUser(dto.getUsername(), dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(Map.of(
                "id", u.getId(), "username", u.getUsername(), "email", u.getEmail()));
    }

    /** login */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInfo dto, HttpSession session) {
        User u = userService.validateLogin(dto.getEmail(), dto.getPassword());
        session.setAttribute("uid", u.getId());
        return ResponseEntity.ok(Map.of(
                "id", u.getId(), "username", u.getUsername(), "email", u.getEmail()));
    }

    /** logout */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) return ResponseEntity.status(401).build();
        User u = userService.getById(uid);
        return ResponseEntity.ok(Map.of(
                "id", u.getId(), "username", u.getUsername(), "email", u.getEmail()));
    }

}
