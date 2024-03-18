package com.easyfolio.esf.food.controller;

import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
//    푸드 메인
    @GetMapping("/foodMain")
    public String cscForm(Model model){
        // 여기에 음식정보 가져와야함
        model.addAttribute("foodList", foodService.allFoodList());
        System.out.println(model);
        return "content/food/food_main";
    }

//    // 음식 상세 조회 페이지
//    @GetMapping("/foodDetailForm")
//    public String foodDetailForm(Model model, FoodVO foodVO){
//        model.addAttribute("foodDetail", foodService.foodDetail(foodVO));
//        return "content/food/food_detail";
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
//    public String updateFoodForm(){
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
//    @PostMapping("/deleteFood")
//    public String deleteFood(FoodVO foodVO){
//        foodService.deleteFood(foodVO);
//        return "redirect:/food/foodMain";
//    }
//
//    // 음식명 조회
//    @GetMapping("/foodNameList")
//    public String foodNameList(Model model, FoodVO foodVO){
//        model.addAttribute("foodNameList", foodService.foodNameList(foodVO));
//        return "content/food/food_nameList";
//    }
//
//    // 음식 타입 조회
//    @GetMapping("/foodTypeList")
//    public String foodTypeList(Model model, FoodVO foodVO){
//        model.addAttribute("foodTypeList", foodService.foodTypeList(foodVO));
//        return "content/food/food_typeList";
//    }
//
//    // 음식 재료 조회
//    @GetMapping("/foodMTRLList")
//    public String foodMTRLList(Model model, FoodVO foodVO){
//        model.addAttribute("foodMTRLList", foodService.foodMTRLList(foodVO));
//        return "content/food/food_mtrlList";
//    }
//
//    // 음식 종류 조회
//    @GetMapping("/foodKindList")
//    public String foodKindList(Model model, FoodVO foodVO){
//        model.addAttribute("foodKindList", foodService.foodKindList(foodVO));
//        return "content/food/food_kindList";
//    }
//
//    // 음식 양 조회
//    @GetMapping("/foodServeList")
//    public String foodServeList(Model model, FoodVO foodVO){
//        model.addAttribute("foodServeList", foodService.foodServeList(foodVO));
//        return "content/food/food_serveList";
//    }
//
//    // 음식 조리 난이도 조회
//    @GetMapping("/foodDifficultList")
//    public String foodDifficultList(Model model, FoodVO foodVO){
//        model.addAttribute("foodDifficultList", foodService.foodDifficultList(foodVO));
//        return "content/food/food_difficultList";
//    }
//
//    // 음식 조리 시간 조회
//    @GetMapping("/foodTimeList")
//    public String foodTimeList(Model model, FoodVO foodVO){
//        model.addAttribute("foodTimeList", foodService.foodTimeList(foodVO));
//        return "content/food/food_timeList";
//    }
//
//    // 음식 등록일 조회
//    @GetMapping("/foodREGDTList")
//    public String foodREGDTList(Model model, FoodVO foodVO){
//        model.addAttribute("foodREGDTList", foodService.foodREGDTList(foodVO));
//        return "content/food/food_regdtList";
//    }
//
//    // 음식 용도 조회
//    @GetMapping("/foodUsageList")
//    public String foodUsageList(Model model, FoodVO foodVO){
//        model.addAttribute("foodUsageList", foodService.foodUsageList(foodVO));
//        return "content/food/food_usageList";
//    }


}
