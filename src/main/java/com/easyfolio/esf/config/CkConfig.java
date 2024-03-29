package com.easyfolio.esf.config;

import jakarta.servlet.http.Cookie;
import jdk.jshell.spi.ExecutionControlProvider;

import java.util.Map;

public class CkConfig {

    //쿠키 리스트를 받아, 해당 이름의 쿠키 반환, 해당 쿠키가 없을 시 생성
    public static Cookie checkCookie(Cookie[] cookies, String cookieName){
        Cookie cookie = null;
        for(Cookie c : cookies){
            cookie = c.getName().equals(cookieName) ? c : null;
        }
        cookie = cookie != null ? cookie : createCookie(cookieName,0);
        return cookie;
    }
    public static Cookie createCookie(String cookieName, int cnt){
        Cookie cookie = new Cookie(cookieName,String.valueOf(cnt));
        System.err.println(cookie.getValue());
        System.err.println("Attribute : " + cookie.getAttribute("0"));
        System.err.println("Attribute : " + cookie.getAttribute(cookieName));
        cookie.setPath("/member");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*1);

        return cookie;
    }

}
