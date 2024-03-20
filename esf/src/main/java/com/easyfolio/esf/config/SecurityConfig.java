package com.easyfolio.esf.config;

import com.easyfolio.esf.config.loginHandler.CustomFailureHandler;
import com.easyfolio.esf.config.loginHandler.CustomLogoutSuccessHandler;
import com.easyfolio.esf.config.loginHandler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
    @Autowired
    private CustomSuccessHandler successHandler;
    @Autowired
    private CustomFailureHandler failureHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        http.csrf(csrf-> csrf.disable()) // 임시 csrf설정 -- > 끔
        .formLogin(formLogin->formLogin
                .loginPage("/member/login")
                .usernameParameter("memberId")
                .passwordParameter("memberPw")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
        ).logout(Customizer ->{
            Customizer.logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .logoutSuccessHandler(logoutSuccessHandler);
                });
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .requestMatchers("/css/**","/js/**", "/img/**").permitAll()
                        .requestMatchers("/","/**","/members/**","/item/**","/images/**", "/extras/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(); // 패스워드 인코더 중 가장 강력
        return NoOpPasswordEncoder.getInstance(); // 임시 설정 -- > 비밀번호 인코딩x
    }

}
