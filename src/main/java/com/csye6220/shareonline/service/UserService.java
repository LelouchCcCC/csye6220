package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.UserDAO;
import com.csye6220.shareonline.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO = new UserDAO();

    /**
     * register new user
     */
    public User registerUser(String username, String email, String password) {
        // 1) check whether username/email is already taken
        if (userDAO.findByUsername(username) != null) {
            throw new RuntimeException("Username already taken.");
        }
        if (userDAO.findByEmail(email) != null) {
            throw new RuntimeException("Email already registered.");
        }

        // 2) （可选）加密密码, 这里简化为原文
        String hashedPassword = password;
        // 生产环境请用 BCrypt, Argon2 等

        // 3) 保存用户
        User newUser = new User(username, email, hashedPassword);
        return userDAO.saveOrUpdateUser(newUser);
    }

    /**
     * 用户登录
     */
    public String loginUser(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid email or password.");
        }

        // 比对密码（这里直接比对原文）
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password.");
        }

        // 如果登录成功，可以生成 JWT (示例)
        String token = generateJwtToken(user);
        return token;
    }

    /**
     * 生成 JWT (简化示例)
     */
    private String generateJwtToken(User user) {
        // 使用常见的 JWT 库(如 io.jsonwebtoken.Jwts) 进行签发
        // 这里只是演示返回一个字符串
        return "fake-jwt-token-for-user-" + user.getId();
    }
}
