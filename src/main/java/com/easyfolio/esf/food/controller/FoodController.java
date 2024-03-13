package com.easyfolio.esf.food.controller;

import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

//    푸드 메인
//    @GetMapping("/foodMain")
//    public String cscForm(Model model){
//        // 여기에 음식정보 가져와야함
//    return "content/food/food_main";
//    }
}
