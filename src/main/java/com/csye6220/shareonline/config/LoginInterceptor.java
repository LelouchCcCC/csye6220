package com.csye6220.shareonline.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * check uid in session
 */
public class LoginInterceptor implements HandlerInterceptor {

    // LoginInterceptor.java
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object h) {

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) return true;

        // /api/auth request can pass directly
        String uri = req.getRequestURI();
        if (uri.startsWith("/api/auth/")) return true;

        if (req.getSession().getAttribute("uid") == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }


}
