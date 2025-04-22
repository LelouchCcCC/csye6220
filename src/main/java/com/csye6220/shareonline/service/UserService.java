package com.csye6220.shareonline.service;

import com.csye6220.shareonline.dao.UserDAO;
import com.csye6220.shareonline.model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO = new UserDAO();

    /** register user */
    public User registerUser(String username, String email, String rawPwd) {
        if (userDAO.findByUsername(username) != null) throw new RuntimeException("Username taken");
        if (userDAO.findByEmail(email)    != null)    throw new RuntimeException("Email registered");

        String hashed = BCrypt.hashpw(rawPwd, BCrypt.gensalt(12));
        return userDAO.saveOrUpdateUser(new User(username, email, hashed));
    }

    /** login check */
    public User validateLogin(String email, String rawPwd) {
        User user = userDAO.findByEmail(email);
        if (user == null || !BCrypt.checkpw(rawPwd, user.getPassword()))
            throw new RuntimeException("Invalid email / password");
        return user;
    }

    public User getById(long id) {
        return userDAO.findById(id);
    }
}
