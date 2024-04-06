package com.easyfolio.esf.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;


@Slf4j
public class CheckLoginInterceptor implements HandlerInterceptor {

    private List<String> fetchList = new ArrayList<>();


    //로그인 체크하는 메서드
    public CheckLoginInterceptor addList(String element){
        fetchList.add(element);
        return this;
    }
    public boolean checkLogin(Principal principal) throws Exception{
        if(principal != null && principal.getName() !=null){
            return true;
        }
        throw new NullPointerException();
    }

    //preHandle controller 동작 전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //이전 페이지 정보를 저장할 세션 생성
        HttpSession session = request.getSession();
        //유저의 타겟 페이지 정보 얻기
        String targetURI = request.getRequestURI();
        //세션에 값 입력

        try{
            //유저 정보를 확인
            Principal principal = request.getUserPrincipal();
            checkLogin(principal);
        }catch (Exception e){
            //권한이 있는 사용자가 아닐 시 로그인 화면으로 이동
            //fetch 요청일경우
            if(fetchList.contains(targetURI)){
                response.setStatus(400);
                return false;
            }
            //fetch요청이 아닐경우
            session.setAttribute("target", request.getRequestURI());
            response.sendRedirect("/member/loginForm");
            response.setHeader("Referer","/member/loginForm");
            log.warn("잘못된 접근");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
