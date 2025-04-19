package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.UserDAO;
import com.csye6220.shareonline.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO = new UserDAO();

    @Autowired
    private PasswordEncoder encoder;   // 来自 PasswordConfig

    /** 注册 */
    public User registerUser(String username, String email, String rawPwd) {

        if (userDAO.findByUsername(username) != null)
            throw new RuntimeException("Username already taken.");
        if (userDAO.findByEmail(email) != null)
            throw new RuntimeException("Email already registered.");

        User u = new User(username, email, encoder.encode(rawPwd)); // BCrypt
        return userDAO.saveOrUpdateUser(u);
    }

    /** 登录校验 */
    public User authenticate(String email, String rawPwd) {
        User u = userDAO.findByEmail(email);
        if (u == null || !encoder.matches(rawPwd, u.getPassword()))
            throw new RuntimeException("Invalid email or password.");
        return u;   // 返回整个对象，Controller 里放进 Session
    }
}
