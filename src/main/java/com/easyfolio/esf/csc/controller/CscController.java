package com.easyfolio.esf.csc.controller;

import com.easyfolio.esf.csc.service.CscService;
import com.easyfolio.esf.csc.vo.*;
import com.easyfolio.esf.csc.vo.ann.AnnCateVO;
import com.easyfolio.esf.csc.vo.ann.AnnVO;
import com.easyfolio.esf.csc.vo.inq.InqImgVO;
import com.easyfolio.esf.csc.vo.inq.InqVO;
import com.easyfolio.esf.csc.vo.qna.QnaVO;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.util.UploadUtillInq;
import com.easyfolio.esf.util.UploadUtillRes;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/csc")
@RequiredArgsConstructor
public class CscController {

    private final CscService cscService;
    private final MemberService memberService;

    // 고객 센터 메인 페이지
    @RequestMapping("/cscForm")
    public String cscForm(Model model, AnnVO annVO, QnaVO qnaVO){
        model.addAttribute("annList", cscService.cscSearchAnn(annVO));
        model.addAttribute("qnaList", cscService.cscSearchQna(qnaVO));
        model.addAttribute("allSearchKeyword", annVO.getAllSearchKeyword());
        return "content/csc/csc_main";
    }
    
    // 공지 사항 //

    // 공지 사항 목록 조회 페이지
    @RequestMapping("/annListForm")
    public String annListForm(Model model, AnnVO annVO, AnnCateVO annCateVO){
        annVO.setTotalDataCnt(cscService.cscSearchAnnCnt(annVO));
        annVO.setPageInfo();
        model.addAttribute("nowPage", annVO.getNowPage());
        model.addAttribute("annCate", annVO.getAnnCate());
        model.addAttribute("annList", cscService.cscSearchAnn(annVO));
        model.addAttribute("annCateList", cscService.annCateList(annCateVO));
        model.addAttribute("allSearchKeyword", annVO.getAllSearchKeyword());
        if (annVO.getAnnCate() != null){
            model.addAttribute("annCate", annVO.getAnnCate());
        }
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
    public String insertAnn(AnnVO annVO, Principal principal){
        annVO.setAnnWriter(principal.getName());
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
    public String updateAnn(AnnVO annVO, Model model){
        cscService.updateAnn(annVO);
        model.addAttribute("annDetail", cscService.annDetail(annVO));

        return "content/csc/ann/csc_annDetail";
    }

    // 공지 사항 삭제 후 목록 페이지 이동
    @GetMapping("/deleteAnn")
    public String deleteAnn(AnnVO annVO){
        cscService.deleteAnn(annVO);
        return "redirect:/csc/annListForm";
    }

    // 문의 사항 //

    // 문의 사항 목록 페이지
    @RequestMapping("/inqListForm")
    public String inqListForm(Model model, Principal principal, InqVO inqVO){
        String user = principal.getName();
        String role = memberService.findMemberById(user).getMemberRole();
        if(!role.equals("ADMIN")){
            inqVO.setInqWriter(principal.getName());

        }
        model.addAttribute("inqList", cscService.inqList(inqVO));
        return "content/csc/inq/csc_inqList";
    }

    // 문의 사항 세부 페이지
    @GetMapping("/inqDetailForm")
    public String inqDetailForm(Model model, InqVO inqVO, ResVO resVO){
        InqVO inq = cscService.inqDetail(inqVO);
        model.addAttribute("inqDetail", cscService.inqDetail(inqVO));
        model.addAttribute("inqImgList", cscService.inqImgList(inqVO));

        if (inq.getInqResponse().equals("Y")){
            model.addAttribute("resInq", cscService.resInq(resVO));
            resVO.setResCode((cscService.resInq(resVO)).getResCode());
            model.addAttribute("resImgList", cscService.resImgList(resVO));
        }

        return "content/csc/inq/csc_inqDetail";
    }

    // 문의 사항 작성 페이지
    @GetMapping("/insertInqForm")
    public String insertInqForm(){

        return "content/csc/inq/csc_insertInq";
    }

    // 문의 사항 작성 후 목록 페이지 이동
    @PostMapping("/insertInq")
    public String insertInq(Principal principal,InqVO inqVO,  @RequestParam("inqImg") MultipartFile[] inqImg){
        //--- 상품 이미지 등록 ---//
        inqVO.setInqWriter(principal.getName());
        //0. 다음에 들어가야 할 ITEM_CODE를 조회
        String inqCode = cscService.nextInqCode();

        //2. 이미지 정보 하나가 들어갈 수 있는 통!

        //첨부파일 기능 다중
        List<InqImgVO> imgList = UploadUtillInq.multiFileUpload(inqImg);


        for(InqImgVO inqImgVO : imgList){
            inqImgVO.setInqCode(inqCode);
        }

        inqVO.setInqImgList(imgList);

        //상품 등록  + 이미지 등록 쿼리
        inqVO.setInqCode(inqCode);
        cscService.insertInq(inqVO);
        return "redirect:/csc/inqListForm";
    }

    // 문의 사항 삭제 후 목록 페이지 이동
    @Transactional
    @GetMapping("/deleteInq")
    public String deleteInq(InqVO inqVO, ResVO resVO){
        if (resVO.resCode!=null ||inqVO.inqResponse.equals("Y")){
            cscService.deleteResImg(resVO);
            cscService.deleteResponse(resVO);
        }
        cscService.deleteInqImg(inqVO);
        cscService.deleteInq(inqVO);
        return "redirect:/csc/inqListForm";
    }
    
    // 문의 사항 답변 페이지
    @GetMapping("/responseInqForm")
     public String responseInqForm(Model model, InqVO inqVO){
        model.addAttribute("inqDetail", cscService.inqDetail(inqVO));
        model.addAttribute("inqImgList", cscService.inqImgList(inqVO));
        return "content/csc/inq/csc_responseInq";
     }
    
    // 문의 사항 답변 후 상세 페이지 이동
    @PostMapping("/insertResponse")
    public String insertResponse(ResVO resVO, @RequestParam("resImg") MultipartFile[] resImg, InqVO inqVO){
        //--- 상품 이미지 등록 ---//
        //0. 다음에 들어가야 할 ITEM_CODE를 조회
        String resCode = cscService.nextResCode();

        //2. 이미지 정보 하나가 들어갈 수 있는 통!

        //첨부파일 기능 다중
        List<ResImgVO> imgList = UploadUtillRes.multiFileUpload(resImg);


        for(ResImgVO resImgVO : imgList){
            resImgVO.setResCode(resCode);
        }

        resVO.setResImgList(imgList);

        //상품 등록  + 이미지 등록 쿼리
        resVO.setResCode(resCode);
        cscService.insertResponse(resVO);
        cscService.updateResponse(inqVO);
        return "redirect:/csc/inqDetailForm?inqCode=" + resVO.getInqCode();
    }
    
    
    
    // 자주 찾는 질문 //

    // 자주 찾는 질문 목록 조회
    @RequestMapping("/qnaListForm")
    public String qnaListForm(Model model, QnaVO qnaVO){
        qnaVO.setTotalDataCnt(cscService.cscSearchQnaCnt(qnaVO));
        qnaVO.setPageInfo();
        model.addAttribute("nowPage", qnaVO.getNowPage());
        model.addAttribute("qnaCode", qnaVO.getQnaCode());
        model.addAttribute("qnaList", cscService.cscSearchQna(qnaVO));
        model.addAttribute("allSearchKeyword", qnaVO.getAllSearchKeyword());
        return "content/csc/qna/csc_qnaList";
    }

    // 자주 찾는 질문 작성 페이지
    @GetMapping("/insertQnaForm")
    public String insertQnaForm(){

        return "content/csc/qna/csc_insertQna";
    }
    
    // 자주 찾는 질문 작성 후 목록 페이지 이동
    @PostMapping("/insertQna")
    public String insertQna(QnaVO qnaVO){
        cscService.insertQna(qnaVO);
        return "redirect:/csc/qnaListForm";
    }

    // 자주 찾는 질문 삭제
    @GetMapping("/deleteQna")
    public String deleteQna (QnaVO qnaVO){
        cscService.deleteQna(qnaVO);
        return "redirect:/csc/qnaListForm";

    }

//     자주 찾는 질문 수정 (비동기)

    @PostMapping("/updateQna")
    public String updateQna(QnaVO qnaVO){
        cscService.updateQna(qnaVO);
        return "redirect:/csc/qnaListForm";
    }













}
