//package com.savchuk.config;
//
//import com.savchuk.dao.entitties.User;
//import com.savchuk.services.CryptoService;
//import com.savchuk.services.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Enumeration;
//
///**
// * Created by home on 09.05.17.
// */
//@Component
//public class AuthFilter implements Filter {
//    @Autowired
//    private IUserService userService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, X-Authentication-token, Access-Control-Request-Headers");
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if(request.getHeader("X-Authentication-token") != null){
//            User user = userService.findUserById(Long.valueOf(CryptoService.decryptAes(request.getHeader("X-Authentication-token"))));
//            if(user != null){
//                request.setAttribute("AuthUserId", user.getId());
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//        }
//        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
////        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
