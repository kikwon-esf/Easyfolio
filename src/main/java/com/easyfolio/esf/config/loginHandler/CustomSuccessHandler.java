package com.easyfolio.esf.config.loginHandler;

import com.easyfolio.esf.config.Transfer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        HttpSession session = request.getSession(); // 세션 영역 불러오기
//        System.err.println("user : "+request.getRemoteUser());
//        System.err.println("user : "+request.getRemoteAddr());
//        System.err.println("user : "+request.getRemoteHost());
//        System.err.println("user : "+request.getRemotePort());
        response.sendRedirect("/"); // 로그인 성공시 url
    }



}
