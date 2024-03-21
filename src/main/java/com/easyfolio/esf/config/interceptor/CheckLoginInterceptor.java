package com.easyfolio.esf.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

    @Component
    @Slf4j
    @RequiredArgsConstructor
    public class CheckLoginInterceptor implements HandlerInterceptor {

        //로그인 체크하는 메서드
        public boolean checkLogin(Principal principal){
            if(principal != null && principal.getName() !=null){
                return true;
            }
            return false;
        }

        //preHandle controller 동작 전 실행
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            Principal principal = request.getUserPrincipal();
            if(checkLogin(principal)){
                modelAndView.getModel().put("isAuthenticated",true);
            }else{
                modelAndView.getModel().put("isAuthenticated",false);
            }
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }
}
