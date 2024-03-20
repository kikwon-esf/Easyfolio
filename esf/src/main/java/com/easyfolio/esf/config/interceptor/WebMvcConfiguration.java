package com.easyfolio.esf.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final CheckLoginInterceptor checkLoginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //예외 등록(이 요청에는 인터셉터 동작 안함, 불필요한 동작 막음)
        List<String> exceptionPath = new ArrayList<>();
        exceptionPath.add("/css/**");
        exceptionPath.add("/js/**");
        exceptionPath.add("/img/**");
        exceptionPath.add("/**"); // -인터셉터 꺼놓기 힐요할때 확장


        //인터셉터 동작
        registry.addInterceptor(checkLoginInterceptor) // 해당 인터셉터 적용
                .excludePathPatterns(exceptionPath) //list로 등록한 예외 적용
                .addPathPatterns("/none"); //
    }
}
