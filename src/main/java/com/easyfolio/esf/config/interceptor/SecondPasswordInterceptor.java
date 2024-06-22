package com.easyfolio.esf.config.interceptor;

import com.easyfolio.esf.member.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecondPasswordInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String sessionId = request.getRequestedSessionId();
        System.err.println("second");
        if(chkInformSession(sessionId)){

            response.sendRedirect("/myPage/editInform");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean chkInformSession(String sessionId){

        if(PwdEditInterceptor.set.contains(sessionId)){
            return true;
        }
        return false;
    }


}
