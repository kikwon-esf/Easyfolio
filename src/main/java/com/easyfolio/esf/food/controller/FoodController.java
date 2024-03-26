package com.easyfolio.esf.food.controller;

import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;
    //일단은 myPageService추가, 논의 후 foodService에 주입
    private final MyPageService myPageService;

    @GetMapping("/searchFoodPage")
    public String searchFoodAllPage(Model model,FoodVO foodVO, @RequestParam(value = "searchFoodValue", required = false) String searchFoodValue,
                                    @RequestParam(value = "foodMtrlCode", required = false) String foodMtrlCode,
                                    @RequestParam(value = "foodUsageCode", required = false) String foodUsageCode,
                                    @RequestParam(value = "foodKindCode", required = false) String foodKindCode){

        foodVO.setSearchFoodValue(searchFoodValue);
        foodVO.setFoodKindCode(foodKindCode);
        foodVO.setFoodMtrlCode(foodMtrlCode);
        foodVO.setFoodUsageCode(foodUsageCode);
        foodVO.setTotalDataCnt(foodService.foodCnt());
        foodVO.setPageInfo();

        // 음식 전체 리스트

        model.addAttribute("foodList", foodService.searchFoodAll(foodVO));
        // 음식 검색어
        model.addAttribute("searchFoodValue", foodVO.getSearchFoodValue());
        // 검색 음식 개수
        model.addAttribute("searchFoodCnt", foodService.searchFoodCnt(foodVO));

        // 종류별 개수
        model.addAttribute("foodKindList", foodService.foodKindList());
        model.addAttribute("foodKindCode", foodVO.getFoodKindCode());
        if (foodVO.getFoodKindCode() != null) {
            model.addAttribute("foodKind", foodService.foodKindText(foodVO));
        }

        // 상황별 검색
        model.addAttribute("foodUsageList", foodService.foodUsageList());
        model.addAttribute("foodUsageCode", foodVO.getFoodUsageCode());

        if (foodVO.getFoodUsageCode() != null) {
            model.addAttribute("foodUsage", foodService.foodUsageText(foodVO));
        }

        // 재료별 검색
        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
        model.addAttribute("foodMtrlCode", foodVO.getFoodMtrlCode());
        if (foodVO.getFoodMtrlCode() != null) {
            model.addAttribute("foodMtrl", foodService.foodMtrlText(foodVO));
        }

        // 방법별 검색
        model.addAttribute("foodTypeList", foodService.foodTypeList());
        model.addAttribute("foodTypeCode", foodVO.getFoodTypeCode());
        if (foodVO.getFoodTypeCode() != null) {
            model.addAttribute("foodType", foodService.foodTypeText(foodVO));
        }
        model.addAttribute("nowPage", foodVO.getNowPage());


        return "/content/food/food_search";
    }
    @PostMapping("/searchFoodPage")
    public String searchFoodAllPagePost(Model model,FoodVO foodVO, @RequestParam(value = "searchFoodValue", required = false) String searchFoodValue){


        foodVO.setSearchFoodValue(searchFoodValue);
        foodVO.setTotalDataCnt(foodService.foodCnt());
        foodVO.setPageInfo();
        // 음식 전체 리스트
        model.addAttribute("foodList", foodService.searchFoodAll(foodVO));
        // 음식 검색어
        model.addAttribute("searchFoodValue", foodVO.getSearchFoodValue());

        // 검색 음식 개수
        model.addAttribute("searchFoodCnt", foodService.searchFoodCnt(foodVO));

        // 종류별 개수
        model.addAttribute("foodKindList", foodService.foodKindList());
        model.addAttribute("foodKindCode", foodVO.getFoodKindCode());
        if (foodVO.getFoodKindCode() != null) {
            model.addAttribute("foodKind", foodService.foodKindText(foodVO));
        }

        // 상황별 검색
        model.addAttribute("foodUsageList", foodService.foodUsageList());
        model.addAttribute("foodUsageCode", foodVO.getFoodUsageCode());

        if (foodVO.getFoodUsageCode() != null) {
            model.addAttribute("foodUsage", foodService.foodUsageText(foodVO));
        }

        // 재료별 검색
        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
        model.addAttribute("foodMtrlCode", foodVO.getFoodMtrlCode());
        if (foodVO.getFoodMtrlCode() != null) {
            model.addAttribute("foodMtrl", foodService.foodMtrlText(foodVO));
        }

        // 방법별 검색
        model.addAttribute("foodTypeList", foodService.foodTypeList());
        model.addAttribute("foodTypeCode", foodVO.getFoodTypeCode());
        if (foodVO.getFoodTypeCode() != null) {
            model.addAttribute("foodType", foodService.foodTypeText(foodVO));
        }

        return "/content/food/food_search";
    }

    //즐겨찾기 추가
    @PostMapping(value = "/addFav")
    @ResponseBody
    public ResponseEntity<String> addFav(Principal principal, @RequestBody Map<String,String> foodCode, FavoriteVO favoriteVO){
        log.info("addFav");
        if(principal == null){ //로그인이 안되어 있을 시
            return new ResponseEntity<>("needLogin",HttpStatus.BAD_GATEWAY);
        }
        favoriteVO.setFoodCode(foodCode.get("foodCode"));
        favoriteVO.setMemberId(principal.getName());
        try {
            myPageService.addFav(favoriteVO);
        }catch (DuplicateKeyException e){ //이미 add가 되어 있을 시 작동(즐겨찾기 삭제)
            myPageService.deleteFav(favoriteVO);
            return new ResponseEntity<>("deleteComplete",HttpStatus.OK);
        }catch (Exception e){ //그 외 예외
            return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("addComplete", HttpStatus.OK);
    }


}
