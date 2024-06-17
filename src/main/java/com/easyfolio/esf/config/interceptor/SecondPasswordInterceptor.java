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

        HttpSession session = request.getSession();
        System.err.println("second");
        if(chkInformSession(session)){

            response.sendRedirect("/myPage/editInform");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean chkInformSession(HttpSession session){
        String value = (String)session.getAttribute("authenticatedInform");
        if(value != null && value.equals("true")){
            return true;
        }
        return false;
    }


}
