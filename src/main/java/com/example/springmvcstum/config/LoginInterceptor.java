package com.example.springmvcstum.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            return true;
        }
        String uri = request.getRequestURI();
        if (uri.startsWith("/login") || uri.startsWith("/css") || uri.startsWith("/js") || uri.startsWith("/images")) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}
