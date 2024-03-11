package com.easyfolio.esf.csc.controller;

import com.easyfolio.esf.csc.service.CscService;
import com.easyfolio.esf.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csc")
@RequiredArgsConstructor
public class CscController {

    private final CscService cscService;

    @GetMapping("/cscForm")
    public String cscForm(Model model){
        model.addAttribute("annList", cscService.mainAnnList());
        return "content/csc/csc_main";
    }

    @RequestMapping("/annForm")
    public String annForm(Model model){
        model.addAttribute("annList", cscService.annList());
        return "content/csc/csc_annList";
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

    @GetMapping("/insertAnnForm")
    public String insertAnnForm(){

        return "content/csc/insert_ann";
    }



}
