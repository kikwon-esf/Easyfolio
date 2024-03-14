package com.easyfolio.esf.csc.controller;

import com.easyfolio.esf.csc.service.CscService;
import com.easyfolio.esf.csc.vo.AnnVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    // 공지 사항 //

    // 공지 사항 목록 조회 페이지
    @RequestMapping("/annForm")
    public String annForm(Model model){
        model.addAttribute("annList", cscService.annList());
        return "content/csc/csc_annList";
    }

    // 공지 사항 세부 조회 페이지
    @GetMapping("/annDetailForm")
    public String annDetailForm(Model model, AnnVO annVO){
        model.addAttribute("annDetail", cscService.annDetail(annVO));
        System.out.println(cscService.annDetail(annVO));
        return "content/csc/csc_annDetail";
    }

    // 공지 사항 작성 페이지
    @GetMapping("/insertAnnForm")
    public String insertAnnForm(){

        return "content/csc/csc_insertAnn";
    }
    
    // 공지 사항 작성 후 목록 이동
    @PostMapping("/insertAnn")
    public String insertAnn(AnnVO annVO){
        cscService.insertAnn(annVO);
        return "redirect:/csc/annForm";
    }

    // 공지 사항 수정 페이지
    @GetMapping("/updateAnn")
    public String updateAnn(){

        return "content/csc/update_ann";
    }

    // 공지 사항 수정 후 목록 상세 페이지 이동



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
