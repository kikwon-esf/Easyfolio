package com.easyfolio.esf.mainpage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainpage")
@RequiredArgsConstructor
public class MainpageController {
    @GetMapping("/main")
    public String mainpage(){
        return "content/indexpage/mainpage";
    }
}
