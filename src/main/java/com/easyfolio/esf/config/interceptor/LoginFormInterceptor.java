package com.easyfolio.esf.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFormInterceptor implements HandlerInterceptor {
    private final List<String> urlList;
    public LoginFormInterceptor(){
        urlList = new ArrayList<>();
        urlList.add("http://localhost:8081/member/changePw");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String Referer = request.getHeader("Referer");
        System.err.println("Referer : " + Referer);
        System.err.println(checkingReferrer(Referer));

        if(checkingReferrer(Referer)){
            response.sendRedirect("/member/loginForm");
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    private boolean checkingReferrer(String Referer){
        for(int i = 0 ; i < urlList.size() ; i++){
            return Referer.equals(urlList.get(i));
        }
        return false;
    }




}
