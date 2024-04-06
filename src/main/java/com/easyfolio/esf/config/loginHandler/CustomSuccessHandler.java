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
        HttpSession session = request.getSession(); //로그인 성공 시 세션 체크
        String target = (String) session.getAttribute("target");
        if(target != null){ //특정 타겟이 있을시(로그인 페이지로 가기 전에 타겟 페이지가 있을 시)
            response.sendRedirect("http://localhost:8081" + target);
            session.removeAttribute("target");
        }else { //그게 아니라면
            String redirectPage = (String)session.getAttribute("prevPage"); //세션에서 이전 페이지 값 추출
            response.sendRedirect(redirectPage != null ? redirectPage : "/"); // 세션에서 이전 페이지 값이 없을시, 메인페이지로 이동
        }
    }



}
