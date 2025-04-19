package com.csye6220.shareonline.controller;

import com.csye6220.shareonline.model.User;
import com.csye6220.shareonline.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /** 注册：接收表单字段，注册后自动登录并跳转到 feed */
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        User user = userService.registerUser(username, email, password);
        // 注册后把 uid/uname 放入 session
        session.setAttribute("uid", user.getId());
        session.setAttribute("uname", user.getUsername());
        // 重定向到 feed 页面
        return "redirect:/feed";
    }

    /** 登录：接收表单字段，验证成功后放入 session 并跳转 */
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        User user = userService.authenticate(email, password);
        session.setAttribute("uid", user.getId());
        session.setAttribute("uname", user.getUsername());
        return "redirect:/feed";
    }

    /** 登出：失效 session 并跳回登录页 */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
