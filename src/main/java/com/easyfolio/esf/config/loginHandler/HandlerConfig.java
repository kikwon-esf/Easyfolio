package com.easyfolio.esf.config.loginHandler;

import com.easyfolio.esf.otherProtocol.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class HandlerConfig {
    private final SseService sseService;
    @Bean
    public CustomFailureHandler failureHandler(){
        return new CustomFailureHandler();
    }
    @Bean
    public CustomLogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler(sseService);
    }
}
