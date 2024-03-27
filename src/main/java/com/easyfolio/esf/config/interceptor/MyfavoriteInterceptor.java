package com.easyfolio.esf.config.interceptor;

import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyfavoriteInterceptor implements HandlerInterceptor {
    private final MyPageService myPageService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            String userId = principal.getName();
            List favoriteList = myPageService.getFavoriteListString(new MemberVO().withMemberId(userId));
            modelAndView.addObject("favoriteList",objectMapper.writeValueAsString(favoriteList));
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
