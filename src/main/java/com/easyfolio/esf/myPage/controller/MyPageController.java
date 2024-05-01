package com.easyfolio.esf.myPage.controller;


import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.CommentVO;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.Banner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/myPage")
public class MyPageController {

    private final MemberService memberService;
    private final FoodService foodService;
    private final MyPageService myPageService;

    @GetMapping(value = "/favorite")
    public String favoritePage(@RequestParam(value = "searchFavoriteValue", required = false) String searchFavoriteValue,FavoriteVO favoriteVO,Principal principal, Model model, MemberVO memberVO){

        favoriteVO.setMemberId(principal.getName());
        favoriteVO.setSearchFavoriteValue(searchFavoriteValue);
        favoriteVO.setTotalDataCnt(myPageService.favoriteCnt(favoriteVO));
        favoriteVO.setPageInfo(8);
        model.addAttribute("searchFavoriteCnt", favoriteVO.getTotalDataCnt());
        List<FavoriteVO> favorite = myPageService.getFavoriteListByMember(favoriteVO);
        model.addAttribute("myFavorite", favorite);

        return "content/myPage/myPage_favorite";
    }
    @ResponseBody
    @PostMapping(value = "/deleteFav")
    public ResponseEntity<String> deleteFavorite(Principal principal, @RequestBody Map<String, String> foodCode, FavoriteVO favoriteVO){

        favoriteVO.setFoodCode(foodCode.get("foodCode"));
        favoriteVO.setMemberId(principal.getName());

        int result = myPageService.deleteFav(favoriteVO);
        if(result == 1){
            return new ResponseEntity<>("complete", HttpStatus.OK);
        }else if(result == 0){
            return new ResponseEntity<>("0", HttpStatus.GONE);
        }
        return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
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


    //즐겨찾기 추가
    @PostMapping(value = "/addFav")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> addFav(Principal principal, @RequestBody Map<String,String> foodCode, FavoriteVO favoriteVO){

        if(principal == null){ //로그인이 안되어 있을 시
            return new ResponseEntity<>("needLogin",HttpStatus.BAD_GATEWAY);
        }
        favoriteVO.setFoodCode(foodCode.get("foodCode"));
        favoriteVO.setMemberId(principal.getName());
        try {
            
            myPageService.addFav(favoriteVO);
            myPageService.increaseFavCnt(favoriteVO);
        }catch (DuplicateKeyException e){ //이미 add가 되어 있을 시 작동(즐겨찾기 삭제)
            myPageService.deleteFav(favoriteVO);
            myPageService.decreaseFavCnt(favoriteVO);
            return new ResponseEntity<>("deleteComplete",HttpStatus.OK);
        }catch (Exception e){ //그 외 예외
            return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("addComplete", HttpStatus.OK);
    }



    //댓글 등록 후 댓글창 업로드
    @PostMapping("/comment")
    public String submitComment(MemberVO memberVO,CommentVO commentVO, Principal principal,  Model model){
        memberService.alamCntPlus(memberVO);
        commentVO.setFoodCommentId(myPageService.nextComtCode());
        commentVO.setReciveMemberId(commentVO.getMemberId());
        commentVO.setSendMemberId(principal.getName());

        myPageService.submitComment(commentVO); // 댓글 등록하는 코드
        memberService.insertAlam(commentVO);

        List<CommentVO> commentList = myPageService.getCommentVOList(commentVO.withMemberId(null));
        model.addAttribute("commentList", commentList);
        model.addAttribute("inputComment",new CommentVO());
        return "content/myPage/replace/food_comment";
    }

}
