package com.csye6220.shareonline.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object h) throws Exception {
        if (req.getSession().getAttribute("uid") == null) {
            res.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
