package com.easyfolio.esf.mainpage.controller;


import com.easyfolio.esf.csc.service.CscService;


import com.easyfolio.esf.csc.vo.ann.AnnVO;
import com.easyfolio.esf.food.service.FoodService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mainpage")
@RequiredArgsConstructor
public class MainpageController {
    // 메인페이지
    @GetMapping("/main")
    public String mainpage(HttpServletRequest request){
        return "content/indexpage/mainpage";
    }

    // 음식검색, 공지사항, 게시판 등등 모든 결과 검색
    @Autowired
    private CscService cscService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/wideSearch")
    public String wideSearch(@RequestParam(value = "allSearchKeyword", required = false) String allSearchKeyword, Model model, AnnVO annVO) {
            annVO.setAllSearchKeyword(allSearchKeyword);
            model.addAttribute("asAnnList", cscService.allSearchAnn(allSearchKeyword));
            model.addAttribute("asFoodList", foodService.allSearchFood(allSearchKeyword));
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
