package com.easyfolio.esf.config.loginHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class HandlerConfig {
    @Bean
    public CustomSuccessHandler successHandler(){
        return new CustomSuccessHandler();
    }
    @Bean
    public CustomFailureHandler failureHandler(){
        return new CustomFailureHandler();
    }
    @Bean
    public CustomLogoutSuccessHandler logoutSuccessHandler(){return new CustomLogoutSuccessHandler(); }
}
