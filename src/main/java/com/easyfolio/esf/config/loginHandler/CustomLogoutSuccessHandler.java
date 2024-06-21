package com.easyfolio.esf.config.loginHandler;

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
        System.err.println("로그아웃이 동작이 되나요?  :" + authentication.getName());
        sseService.deleteId(authentication.getName());
        String referer = request.getHeader("Referer");
        request.setAttribute("logoutSuccess","logoutSuccess");
        System.err.println("referer"+referer);
        System.err.println("queryString" + request.getQueryString());
        System.err.println("requestURL" + request.getRequestURL());
        System.err.println("sessionId" + request.getRequestedSessionId());
        response.sendRedirect(referer);
    }
}
