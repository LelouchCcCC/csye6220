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

        // 2) TODO: encode here
        String hashedPassword = password;

        User newUser = new User(username, email, hashedPassword);
        return userDAO.saveOrUpdateUser(newUser);
    }

    /**
     * login
     */
    public String loginUser(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid email or password.");
        }

        // using raw comparation temperatily
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password.");
        }

        // TODO: use JWT later
        String token = generateJwtToken(user);
        return token;
    }

    /** TODO:
     * JWT
     */
    private String generateJwtToken(User user) {
        return "JWT:" + user.getId();
    }
}
