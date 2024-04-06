package com.easyfolio.esf.food.controller;

import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;
    //일단은 myPageService추가, 논의 후 foodService에 주입
    private final MyPageService myPageService;

    @RequestMapping(value = "/searchFoodPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchFoodAllPage(Model model, FoodVO foodVO,
                                    Principal principal, MemberVO memberVO) throws Exception {

        if (RequestMethod.POST.toString().equals(RequestContextHolder.currentRequestAttributes().getAttribute("method", RequestAttributes.SCOPE_REQUEST))) {
            setupFavoriteList(model, principal, memberVO);
        }
        foodVO.setTotalDataCnt(foodService.searchFoodCnt(foodVO));
        foodVO.setPageInfo();
        model.addAttribute("nowPage", foodVO.getNowPage());

        setupFoodList(model, foodVO);
        setupSearchDetails(model, foodVO);

        return "/content/food/food_search";
    }

    private void setupFavoriteList(Model model, Principal principal, MemberVO memberVO) throws JsonProcessingException {
        if (principal != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            memberVO.setMemberId(principal.getName());
            List<String> favoriteList = myPageService.getFavoriteListString(memberVO);
            model.addAttribute("favoriteList", objectMapper.writeValueAsString(favoriteList));
        }
    }

    private void setupFoodList(Model model, FoodVO foodVO) {
        model.addAttribute("foodList", foodService.searchFoodAll(foodVO));
    }

    private void setupSearchDetails(Model model, FoodVO foodVO) {
        model.addAttribute("searchFoodValue", foodVO.getSearchFoodValue());
        model.addAttribute("searchFoodCnt", foodService.searchFoodCnt(foodVO));
        model.addAttribute("foodKindList", foodService.foodKindList());
        model.addAttribute("foodKindCode", foodVO.getFoodKindCode());
        if (foodVO.getFoodKindCode() != null) {
            model.addAttribute("foodKind", foodService.foodKindText(foodVO));
        }
        model.addAttribute("foodUsageList", foodService.foodUsageList());
        model.addAttribute("foodUsageCode", foodVO.getFoodUsageCode());
        if (foodVO.getFoodUsageCode() != null) {
            model.addAttribute("foodUsage", foodService.foodUsageText(foodVO));
        }
        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
        model.addAttribute("foodMtrlCode", foodVO.getFoodMtrlCode());
        if (foodVO.getFoodMtrlCode() != null) {
            model.addAttribute("foodMtrl", foodService.foodMtrlText(foodVO));
        }
        model.addAttribute("foodTypeList", foodService.foodTypeList());
        model.addAttribute("foodTypeCode", foodVO.getFoodTypeCode());
        if (foodVO.getFoodTypeCode() != null) {
            model.addAttribute("foodType", foodService.foodTypeText(foodVO));
        }
    }


    @GetMapping(value = "detail")
    public String foodDtl(Model model, FoodVO foodVO) {
        foodService.updateFoodInqCnt(foodVO);
        model.addAttribute("foodDetail", foodService.getFoodDtl(foodVO));
        FoodVO detailFoodVO = foodService.getFoodDtl(foodVO);
        model.addAttribute("foodCodeList", foodService.selectFoodCode(detailFoodVO));
        setupSearchDetails(model, foodVO);
        System.out.println();
        String mtrl = detailFoodVO.getFoodMtrlCn();
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]([^\\[]+)(?=\\[|$)");
        Matcher matcher = pattern.matcher(mtrl);

        List<String> mtrlTitle = new ArrayList<>();
        List<List<String>> mtrlMt1 = new ArrayList<>();
        List<List<String>> mtrlMt2 = new ArrayList<>();

        while (matcher.find()) {
            mtrlTitle.add(matcher.group(1).trim());
            String materials = matcher.group(2).trim();
            String[] mtrls = materials.split("\\|");
            for (int i = 0; i < mtrls.length; i++) {
                String[] splitMtrls = mtrls[i].trim().split("\\s+(?=[^\\s]*$)");
                List<String> mtrlList = Arrays.asList(splitMtrls);
                if (mtrlTitle.size() == 1) {
                    mtrlMt1.add(mtrlList);
                } else {
                    mtrlMt2.add(mtrlList);
                }
            }
        }

        // 결과 출력
        model.addAttribute("mtrlTitles", mtrlTitle);
        model.addAttribute("mtrlMt1", mtrlMt1);
        if (mtrlMt2.isEmpty()) {
            mtrlMt2.add(new ArrayList<>());
        }
        model.addAttribute("mtrlMt2", mtrlMt2);

        return "/content/food/food_detail";
    }
}
