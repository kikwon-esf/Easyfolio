package com.easyfolio.esf.config.loginHandler;

import com.easyfolio.esf.config.CkConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
@Component
@Slf4j
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //로그인 횟수를 받아 오는 쿠키
        Cookie cookie = CkConfig.checkCookie(request.getCookies(),"loginCnt");

        //쿠키의 value ++
        int setCnt = (Integer.parseInt(cookie.getValue())) + 1;
        cookie.setValue(String.valueOf(setCnt));

        //쿠키 저장
        response.addCookie(cookie);
        response.sendRedirect("/member/loginForm/error");
    }
}
