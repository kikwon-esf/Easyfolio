package com.easyfolio.esf.csc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csc")
@RequiredArgsConstructor
public class CscController {

    @GetMapping("/cscForm")
    public String cscForm(){

        return "content/csc/csc_main";
    }


}
