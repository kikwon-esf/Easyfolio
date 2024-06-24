package com.easyfolio.esf.mainpage.controller;


import com.easyfolio.esf.csc.service.CscService;


import com.easyfolio.esf.csc.vo.ann.AnnVO;
import com.easyfolio.esf.csc.vo.qna.QnaVO;
import com.easyfolio.esf.food.service.FoodService;

import com.easyfolio.esf.member.service.AlarmService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mainpage")
@RequiredArgsConstructor
public class MainpageController {
    @Resource
    private final MemberService memberService;
    private final AlarmService alarmService;

    // 메인페이지
    @GetMapping("/main")
    public String mainpage(Principal principal, MemberVO memberVO, Model model, QnaVO qnaVO, AnnVO annVO){
        model.addAttribute("annList", cscService.cscSearchAnn(annVO));
        model.addAttribute("qnaList", cscService.cscSearchQna(qnaVO));
        List<AlarmVO> alarmList = null;
        if(principal != null && principal.getName() != null){
            memberVO.setMemberId(principal.getName());
            alarmList = alarmService.alarmList(memberVO);
        } else {
            // principal이 null이거나 getName()이 null인 경우에 대한 처리
        }

        model.addAttribute("alarmList", alarmList);
        return "content/indexpage/mainpage";
    }

    // 음식검색, 공지사항, 게시판 등등 모든 결과 검색
    @Autowired
    private CscService cscService;

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/wideSearch", method = {RequestMethod.GET/*, RequestMethod.POST*/})
    public String wideSearch(@RequestParam(value = "allSearchKeyword", required = false) String allSearchKeyword, Model model, AnnVO annVO) {
            annVO.setAllSearchKeyword(allSearchKeyword);
            model.addAttribute("asAnnList", cscService.allSearchAnn(allSearchKeyword));
            model.addAttribute("asFoodList", FoodController.setCommentCnt(foodService.allSearchFood(allSearchKeyword),myPageService));
            model.addAttribute("asFoodCnt", foodService.allSearchFoodCnt(allSearchKeyword));
            model.addAttribute("asQnaList", cscService.allSearchQna(allSearchKeyword));
            model.addAttribute("allSearchKeyword", allSearchKeyword);
            return "content/indexpage/wideSearch";
    }



    @GetMapping("/test")
    public String testpage(){
        return "content/indexpage/testpage";
    }

    @GetMapping("/recipe")
    public String recipepage(){
        return "content/food/recipe_detail_copy_copy";
    }
}
