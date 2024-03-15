package com.easyfolio.esf.mainpage.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainpage")
@RequiredArgsConstructor
public class MainpageController {
    @GetMapping("/main")
    public String mainpage(HttpServletRequest request){
        return "content/indexpage/mainpage";
    }

    @GetMapping("/test")
    public String testpage(){
        return "content/indexpage/testpage";
    }
}
