package com.easyfolio.esf.mainpage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainpage")
@RequiredArgsConstructor
public class MainpageController {

    @GetMapping("/main")
    public String homePage(Model model, Authentication authentication) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "content/indexpage/mainpage";
    }
    @GetMapping("/test")
    public String testpage(){
        return "content/indexpage/testpage";
    }
}
