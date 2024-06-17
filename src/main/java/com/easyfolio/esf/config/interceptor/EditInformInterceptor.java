package com.easyfolio.esf.config.interceptor;

import com.easyfolio.esf.member.service.LoginService;
import com.easyfolio.esf.member.service.MemberService;
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
public class EditInformInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        System.err.println("edit");
        if(!chkInformSession(session)){
            response.sendRedirect("/myPage/myDetails");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean chkInformSession(HttpSession session){
        String value = (String)session.getAttribute("authenticatedInform");
        System.err.println(value);
        if(value != null && value.equals("true")){
            session.setAttribute("authenticatedInform","used");
            return true;
        }
        return false;
    }


}
