package com.easyfolio.esf.config.interceptor;

import com.easyfolio.esf.config.PwdEditThread;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class PwdEditInterceptor implements HandlerInterceptor {

    public static CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

    //preHandle controller 동작 전 실행
    private boolean sessionChk(String sessionId){
        return set.contains(sessionId) ? true : false;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userSessionId = request.getRequestedSessionId();
        request.getHttpServletMapping();

        if(!sessionChk(userSessionId)){
            response.sendRedirect("/member/findMemberForm");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
