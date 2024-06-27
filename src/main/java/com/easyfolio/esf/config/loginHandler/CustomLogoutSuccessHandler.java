package com.easyfolio.esf.config.loginHandler;

import com.easyfolio.esf.config.interceptor.PwdEditInterceptor;
import com.easyfolio.esf.otherProtocol.sse.service.SseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private final SseService sseService;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication != null){
            sseService.deleteId(authentication.getName());
        }


        removeSessionId(request.getRequestedSessionId());

        String referer = request.getHeader("Referer");
        System.err.println(referer);
        if(referer.equals("http://localhost:8081/member/changePw")){
            response.sendRedirect("http://localhost:8081/member/loginForm/pwChange");
        }else{
            request.setAttribute("logoutSuccess","logoutSuccess");
            response.sendRedirect(referer);
        }


    }
    private void removeSessionId(String sessionId){
        PwdEditInterceptor.set.remove(sessionId);
    }
}
