package com.easyfolio.esf.config.loginHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class HandlerConfig {

    @Bean
    public CustomFailureHandler failureHandler(){
        return new CustomFailureHandler();
    }
    @Bean
    public CustomLogoutSuccessHandler logoutSuccessHandler(){return new CustomLogoutSuccessHandler(); }
}
