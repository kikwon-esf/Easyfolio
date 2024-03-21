package com.easyfolio.esf.myPage.controller;


import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/myPage")
public class MyPageController {

    private final MemberService memberService;
    private final FoodService foodService;
    private final MyPageService myPageService;

    @GetMapping(value = "/favorite")
    public String favoritePage(Principal principal, Model model){

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(principal.getName());
        System.err.println("memberId : " + memberVO.getMemberId());
        List list = myPageService.getFavoriteListByMember(memberVO);
        for(int i = 0 ; i < list.size() ; i++){
            System.err.println(list.get(i));
        }

        model.addAttribute("myFavorite", list);
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
