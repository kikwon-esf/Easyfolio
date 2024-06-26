package com.easyfolio.esf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "redirect:/mainpage/main";
    }

    @GetMapping("/region")
    public String region() {
        return "redirect:/weather/region";
    }
}
