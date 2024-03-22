package com.easyfolio.esf.myPage.controller;


import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String favoritePage(Principal principal, Model model, MemberVO memberVO){
        memberVO.setMemberId(principal.getName());
        List<FavoriteVO> favorite = myPageService.getFavoriteListByMember(memberVO);

        model.addAttribute("myFavorite", favorite);
        return "content/myPage/myPage_favorite";
    }
    @ResponseBody
    @PostMapping(value = "/deleteFav")
    public ResponseEntity<String> deleteFavorite(Principal principal, @RequestBody String foodCode, FavoriteVO favoriteVO){
        favoriteVO.setFoodCode(foodCode);
        favoriteVO.setMemberId(principal.getName());

        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
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
