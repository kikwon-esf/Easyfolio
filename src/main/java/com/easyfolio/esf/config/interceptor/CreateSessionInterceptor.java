package com.easyfolio.esf.config.interceptor;

import com.easyfolio.esf.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateSessionInterceptor implements HandlerInterceptor {
    private final MemberService memberService;
    //Session안에 이전 페이지를 저장해주는 작업
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try{
            HttpSession session = request.getSession();
            String prevPage = request.getHeader("Referer");
            if(prevPage!=null&&!prevPage.contains("/member/login")&&!prevPage.contains("/member")){
                session.setAttribute("prevPage", request.getHeader("Referer"));
            }

        }catch (Exception e){

        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String user=null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            user = request.getUserPrincipal().getName();
            System.err.println(memberService.findMemberById(user).getMemberRole());
            modelAndView.addObject("memberRole",memberService.findMemberById(user).getMemberRole());
            modelAndView.addObject("user",user);
        }catch (Exception e){

        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
