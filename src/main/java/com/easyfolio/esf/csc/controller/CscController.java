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

    @GetMapping("/annForm")
    public String annForm(){

        return "content/csc/csc_ann";
    }

    @GetMapping("/inqForm")
    public String inqForm(){

        return "content/csc/csc_inq";
    }

    @GetMapping("/inqListForm")
    public String inqListForm(){

        return "content/csc/csc_inqList";
    }

    @GetMapping("/qnaForm")
    public String qnaForm(){

        return "content/csc/csc_qna";
    }



}
