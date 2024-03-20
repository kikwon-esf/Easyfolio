package com.easyfolio.esf.myPage;


import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/myPage")
public class MyPageController {

    private final MemberService memberService;
    private final FoodService foodService;

    @GetMapping(value = "/favorite")
    public String favoritePage(Principal principal, Model model){
        String user = principal.getName();


        return "content/myPage/myPage_favorite";
    }
    @GetMapping(value = "/myContent")
    public String myContent(Principal principal, Model model){
        String user = principal.getName();


        return "content/myPage/myPage_myContent";
    }
    @GetMapping(value = "/myDetails")
    public String myInform(Principal principal, Model model){
        String user = principal.getName();


        return "content/myPage/myPage_myDetails";
    }



}
