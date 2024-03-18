package com.easyfolio.esf.csc.controller;

import com.easyfolio.esf.csc.service.CscService;
import com.easyfolio.esf.csc.vo.AnnVO;
import com.easyfolio.esf.csc.vo.InqImgVO;
import com.easyfolio.esf.csc.vo.InqVO;
import com.easyfolio.esf.util.UploadUtillCsc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/csc")
@RequiredArgsConstructor
public class CscController {

    private final CscService cscService;

    // 고객 센터 메인 페이지
    @GetMapping("/cscForm")
    public String cscForm(Model model){
        model.addAttribute("annList", cscService.mainAnnList());
        return "content/csc/csc_main";
    }
    
    // 공지 사항 //

    // 공지 사항 목록 조회 페이지
    @RequestMapping("/annListForm")
    public String annForm(Model model){
        model.addAttribute("annList", cscService.annList());
        return "content/csc/ann/csc_annList";
    }

    // 공지 사항 세부 조회 페이지
    @GetMapping("/annDetailForm")
    public String annDetailForm(Model model, AnnVO annVO){
        model.addAttribute("annDetail", cscService.annDetail(annVO));
        return "content/csc/ann/csc_annDetail";
    }

    // 공지 사항 작성 페이지
    @GetMapping("/insertAnnForm")
    public String insertAnnForm(){

        return "content/csc/ann/csc_insertAnn";
    }
    
    // 공지 사항 작성 후 목록 이동
    @PostMapping("/insertAnn")
    public String insertAnn(AnnVO annVO){
        cscService.insertAnn(annVO);
        return "redirect:/csc/annListForm";
    }

    // 공지 사항 수정 페이지
    @GetMapping("/updateAnnForm")
    public String updateAnnForm(Model model, AnnVO annVO){
        model.addAttribute("annDetail", cscService.annDetail(annVO));
        return "content/csc/ann/csc_updateAnn";
    }

    // 공지 사항 수정 후 목록 상세 페이지 이동
    @PostMapping("/updateAnn")
    public String updateAnn(AnnVO annVO){
        cscService.updateAnn(annVO);
        return "redirect:/csc/annListForm?annNum=\" + annVO.getAnnNum();";
    }

    // 공지 사항 삭제
    @GetMapping("/deleteAnn")
    public String deleteAnn(AnnVO annVO){
        cscService.deleteAnn(annVO);
        return "redirect:/csc/annListForm";
    }

    // 문의 사항 //

    // 문의 사항 목록 페이지
    @RequestMapping("/inqListForm")
    public String inqListForm(Model model){
        model.addAttribute("inqList", cscService.inqList());
        return "content/csc/inq/csc_inqList";
    }

    // 문의 사항 세부 페이지
    @GetMapping("/inqDetailForm")
    public String inqDetailForm(Model model, InqVO inqVO){
        System.out.println(inqVO);
        model.addAttribute("inqDetail", cscService.inqDetail(inqVO.inqCode));
        System.out.println(1);
        model.addAttribute("inqImgList", cscService.inqImgList(inqVO.inqCode));
        return "content/csc/inq/csc_inqDetail";
    }

    // 문의 사항 작성 페이지
    @GetMapping("/insertInqForm")
    public String insertInqForm(){

        return "content/csc/inq/csc_insertInq";
    }

    // 문의 사항 작성 후 목록 페이지 이동
    @PostMapping("/insertInq")
    public String insertInq(InqVO inqVO, MultipartFile[] inqImg){
        System.out.println(inqVO);
        //--- 상품 이미지 등록 ---//
        //0. 다음에 들어가야 할 ITEM_CODE를 조회
        String inqCode = cscService.nextInqCode();

        //2. 이미지 정보 하나가 들어갈 수 있는 통!

        //첨부파일 기능 다중
        List<InqImgVO> imgList = UploadUtillCsc.multiFileUpload(inqImg);


        for(InqImgVO inqImgVO : imgList){
            inqImgVO.setInqCode(inqCode);
        }

        inqVO.setInqImgList(imgList);

        //상품 등록  + 이미지 등록 쿼리
        inqVO.setInqCode(inqCode);
        cscService.insertInq(inqVO);
        return "redirect:/csc/inqListForm";
    }

    // 자주 찾는 질문 //

    @GetMapping("/qnaForm")
    public String qnaForm(){

        return "content/csc/qna/csc_qna";
    }

    @GetMapping("/insertQnaForm")
    public String insertQnaForm(){

        return "content/csc/qna/csc_insertQna";
    }





}
