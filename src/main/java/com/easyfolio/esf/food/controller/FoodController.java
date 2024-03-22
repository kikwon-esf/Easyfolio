package com.easyfolio.esf.food.controller;

import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
//    푸드 메인
    @GetMapping("/foodMain")
    public String foodMainForm(Model model, FoodVO foodVO){
        foodVO.setTotalDataCnt(foodService.foodCnt());
        foodVO.setPageInfo();
        model.addAttribute("foodList", foodService.allFoodList(foodVO));
        model.addAttribute("foodCnt", foodService.foodCnt());
        return "/content/food/food_main";
    }
    @GetMapping("/foodMainPage1")
    public String foodMainPage1(Model model, FoodVO foodVO) {
        model.addAttribute("nowPage", foodVO.getNowPage());
        return "redirect:/food/foodMain?nowPage=" + foodVO.getNowPage();
    }

    @PostMapping("/searchFood")
    public String searchFoodAll(Model model, FoodVO foodVO) {
        foodVO.setTotalDataCnt(foodService.foodCnt());
        foodVO.setPageInfo();
        model.addAttribute("foodList", foodService.searchFoodAll(foodVO));
        model.addAttribute("searchFoodValue", foodVO.getSearchFoodValue());
        model.addAttribute("searchFoodCnt", foodService.searchFoodCnt(foodVO));
        return "/content/food/food_search";
    }
    @GetMapping("/searchFoodPage")
    public String searchFoodAllPage(Model model,FoodVO foodVO, @RequestParam("searchFoodValue") String searchFoodValue){
        foodVO.setSearchFoodValue(searchFoodValue);
        foodVO.setTotalDataCnt(foodService.foodCnt());
        foodVO.setPageInfo();
        model.addAttribute("foodList", foodService.searchFoodAll(foodVO));
        model.addAttribute("searchFoodValue", foodVO.getSearchFoodValue());
        model.addAttribute("searchFoodCnt", foodService.searchFoodCnt(foodVO));
        return "/content/food/food_search";
    }

    // 전체 검색


//    // 음식명 조회
//    @GetMapping("/foodNameList")
//    public String foodNameList(String foodName, Model model){
//        model.addAttribute("foodNameList", foodService.FoodNameList(foodName));
//        return "content/food/food_nameList";
//    }
//
//    // 음식 상세 조회 페이지
//    @GetMapping("/foodDetailForm")
//    public String foodDetailForm(Model model, FoodVO foodVO){
//        model.addAttribute("foodDetail", foodService.foodDetail(foodVO));
//        return "content/food/food_detail";
//    }
//
//    // 음식 타입 검색 api
//    @GetMapping("/foodTypeList")
//    public String foodTypeList(String foodType, Model model){
//        model.addAttribute("foodTypeList", foodService.foodTypeList(foodType));
//        return "content/food/food_typeList";
//    }
//
//    // 음식 재료 검색 api
//    @GetMapping("/foodMtrlList")
//    public String foodMtrlList(String foodMtrlCn, Model model){
//        model.addAttribute("foodMtrlList", foodService.foodMtrlList(foodMtrlCn));
//        return "content/food/food_mtrlList";
//    }
//
//    // 음식 용도 검색 api
//    @GetMapping("/foodUsageList")
//    public String foodUsageList(String foodUsage, Model model){
//        model.addAttribute("foodUsageList", foodService.foodUsageList(foodUsage));
//        return "content/food/food_usageList";
//    }
//
//    // 음식 종류별 검색 api
//    @GetMapping("/foodKindList")
//    public String foodKindList(String foodKind, Model model){
//        model.addAttribute("foodKindList", foodService.foodKindList(foodKind));
//        return "content/food/food_kindList";
//    }
//
//    // 음식 난이도별 검색 api
//    @GetMapping("/foodDifList")
//    public String foodDifList(String foodDif, Model model){
//        model.addAttribute("foodDifList", foodService.foodDifList(foodDif));
//        return "content/food/food_difList";
//    }
//
//    // 음식 양별 검색 api
//    @GetMapping("/foodServeList")
//    public String foodServeList(String foodServe, Model model){
//        model.addAttribute("foodServeList", foodService.foodServeList(foodServe));
//        return "content/food/food_serveList";
//    }
//
//    // 음식 조리시간별 검색 api
//    @GetMapping("/foodTimeList")
//    public String foodTimeList(String foodTime, Model model){
//        model.addAttribute("foodTimeList", foodService.foodTimeList(foodTime));
//        return "content/food/food_timeList";
//    }
//
//    // 음식 등록일별 검색 api
//    @GetMapping("/foodRegDtList")
//    public String foodRegDtList(String foodRegDt, Model model){
//        model.addAttribute("foodRegDtList", foodService.foodRegDtList(foodRegDt));
//        return "content/food/food_regDtList";
//    }
//
//    // 추천수별 음식 목록
//    @GetMapping("/foodRcmmCntList")
//    public String foodRcmmCntList(String foodRcmmCnt, Model model){
//        model.addAttribute("foodRcmmCntList", foodService.foodRcmmCntList(foodRcmmCnt));
//        return "content/food/food_rcmmList";
//    }
//
//    // 음식 조회수별 검색
//    @GetMapping("/foodInqCntList")
//    public String foodInqList(String foodInqCnt, Model model){
//        model.addAttribute("foodInqCntList", foodService.foodInqCntList(foodInqCnt));
//        return "content/food/food_inqList";
//    }
//
//    // 음식 등록 페이지
//    @GetMapping("/insertFoodForm")
//    public String insertFoodForm(){
//        return "content/food/food_insert";
//    }
//
//    // 음식 등록 후 목록 이동
//    @PostMapping("/insertFood")
//    public String insertFood(FoodVO foodVO){
//        foodService.insertFood(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//    // 음식 수정 페이지
//    @GetMapping("/updateFoodForm")
//    public String updateFoodForm(Model model, FoodVO foodVO){
//        model.addAttribute("foodDetail", foodService.foodDetail(foodVO));
//        return "content/food/food_update";
//    }
//
//    // 음식 수정 후 목록 이동
//    @PostMapping("/updateFood")
//    public String updateFood(FoodVO foodVO){
//        foodService.updateFood(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//    // 음식 삭제
//    @GetMapping("/deleteFood")
//    public String deleteFood(FoodVO foodVO){
//        foodService.deleteFood(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//    // 음식 조회수 증가
//    @PostMapping("/updateFoodInqCnt")
//    public String updateFoodInqCnt(FoodVO foodVO){
//        foodService.updateFoodInqCnt(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//    // 음식 조회수 감소
//    @PostMapping("/updateFoodInqCntDown")
//    public String updateFoodInqCntDown(FoodVO foodVO){
//        foodService.updateFoodInqCntDown(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//    // 음식 추천수 증가
//    @PostMapping("/updateFoodRcmmCnt")
//    public String updateFoodRcmmCnt(FoodVO foodVO){
//        foodService.updateFoodRcmmCnt(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//
//    // 음식 추천수 감소
//    @PostMapping("/updateFoodRcmmCntDown")
//    public String updateFoodRcmmCntDown(FoodVO foodVO){
//        foodService.updateFoodRcmmCntDown(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//


}
