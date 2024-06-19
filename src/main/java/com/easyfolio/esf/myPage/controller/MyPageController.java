package com.easyfolio.esf.myPage.controller;


import com.easyfolio.esf.config.Transfer;
import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.service.AlarmService;
import com.easyfolio.esf.member.service.LoginService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.CommentVO;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import com.easyfolio.esf.otherProtocol.sse.service.SseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.Banner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/myPage")
public class MyPageController {

    private final MemberService memberService;
    private final FoodService foodService;
    private final MyPageService myPageService;
    private final AlarmService alarmService;
    private final SseService sseService;
    private final LoginService loginService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/favorite")
    public String favoritePage(@RequestParam(value = "searchFavoriteValue", required = false) String searchFavoriteValue,FavoriteVO favoriteVO,Principal principal, Model model, MemberVO memberVO){

        favoriteVO.setMemberId(principal.getName());
        favoriteVO.setSearchFavoriteValue(searchFavoriteValue);
        favoriteVO.setTotalDataCnt(myPageService.favoriteCnt(favoriteVO));
        favoriteVO.setPageInfo(8);
        model.addAttribute("nowPage", favoriteVO.getNowPage());
        model.addAttribute("searchFavoriteCnt", favoriteVO.getTotalDataCnt());
        List<FavoriteVO> favorite = myPageService.getFavoriteListByMember(favoriteVO);
        model.addAttribute("myFavorite", favorite);

        return "content/myPage/myPage_favorite";
    }
    @ResponseBody
    @PostMapping(value = "/deleteFav")
    public ResponseEntity<String> deleteFavorite(Principal principal, @RequestBody Map<String, String> foodCode, FavoriteVO favoriteVO, Model model){

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

    //내가 쓴 글
    @GetMapping(value = "/myContent")
    public String myContent(Principal principal, Model model, CommentVO commentVO){

        return "content/myPage/myPage_myContent";
    }


    //내가 쓴 food
    @GetMapping(value = "/myContent/food")
    public String myFood(Principal principal, Model model, FoodVO foodVO){
        String user = principal.getName();
        foodVO.setMemberId(user);
        foodVO.setTotalDataCnt(myPageService.foodByMemberCnt(foodVO));
        foodVO.setPageInfo(8);
        List<FoodVO> foodList =  myPageService.getFoodByMember(foodVO);
        model.addAttribute("nowPage", foodVO.getNowPage());
        model.addAttribute("foodList", foodList);
        return "content/myPage/replace/content_food";
    }

    //내가 쓴 댓글
    @GetMapping(value = "/myContent/comment")
    public String myComment(Principal principal, Model model, CommentVO commentVO){
        String user = principal.getName();
        commentVO.setMemberId(user);
        commentVO.setTotalDataCnt(myPageService.commentListCnt(commentVO));
        commentVO.setPageInfo();
        List<CommentVO> commentList = myPageService.getCommentByMember(commentVO);
        int nowPage = commentVO.getNowPage();
        model.addAttribute("commentList",commentList);
        model.addAttribute("nowPage", nowPage);
        return "content/myPage/replace/content_comment";
    }

    @GetMapping(value = "/myDetails")
    public String myInform(Principal principal, Model model){
        String user = principal.getName();

        return "content/myPage/myPage_myDetails";
    }
    @GetMapping(value = "/editInform")
    public String editInform(HttpServletRequest request, Principal principal, Model model){
        HttpSession session = request.getSession();
        session.setAttribute("authenticatedInform","used");
        MemberVO member = memberService.findMemberById(principal.getName());
        member.setMemberPw("");
        model.addAttribute("member", member);
        return "content/myPage/myPage_editInform";
    }
    @Transactional
    @PostMapping(value = "/submitInform")
    @ResponseBody
    public ResponseEntity<String> submitInform(HttpServletRequest request, Principal principal, HttpServletResponse response, MemberVO memberVO){

        HttpSession session = request.getSession();
        session.setAttribute("authenticatedInform","true");
        memberVO.setMemberId(principal.getName());
        System.err.println(memberVO);
        try{
            memberService.updateMember(memberVO);
        }catch (NullPointerException ne){
            ne.printStackTrace();
            response.setStatus(400);
            return new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
        }catch(BadSqlGrammarException se){
            return new ResponseEntity<String>("error",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
    //2차인증
    @PostMapping(value = "/secondPasswordChk")
    public String secondPasswordChk(HttpServletRequest request, HttpServletResponse response, Principal principal, Model model, MemberVO memberVO){
        String user = principal.getName();
        UserDetails userDetails = loginService.loadUserByUsername(user);
        if(chkUser(userDetails, memberVO)) {
            System.err.println("참");
            HttpSession session = request.getSession();
            session.setAttribute("authenticatedInform", "true");
            return null;
        }
        response.setStatus(202);
        return null;
    }
    private boolean chkUser(UserDetails oriUser, MemberVO member){
        String oriPass = oriUser.getPassword();
        String userPw = passwordEncoder.encode(member.getMemberPw());
        return oriPass.equals(userPw) ? true : false;
    }


    //즐겨찾기 추가
    @PostMapping(value = "/addFav")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> addFav(Principal principal, @RequestBody Map<String,String> requestMap, FavoriteVO favoriteVO, CommentVO commentVO, Model model){

        if(principal == null){ //로그인이 안되어 있을 시
            return new ResponseEntity<>("needLogin",HttpStatus.BAD_GATEWAY);
        }
        favoriteVO.setFoodCode(requestMap.get("foodCode"));
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
        }finally {
            model.addAttribute("list", myPageService.getFavoriteListByMember(favoriteVO));
        }

        return new ResponseEntity<>("addComplete", HttpStatus.OK);
    }




    //댓글 등록 후 댓글창 업로드
    @Transactional
    @PostMapping("/comment")
    public String submitComment(MemberVO memberVO,CommentVO commentVO, Principal principal, Model model, HttpServletResponse response){
//        alarmService.alarmCntPlus(memberVO);
        commentVO.setFoodCommentId(myPageService.nextComtCode());
        commentVO.setSendMemberId(principal.getName());
        if(!Transfer.reqexTest(commentVO.getContent())){
            myPageService.submitComment(commentVO); // 댓글 등록하는 코드
            alarmService.insertAlarm(commentVO);
        }else{
            response.setStatus(400);
        }



        String sendMember = commentVO.getReciveMemberId();
        List alarmList = alarmService.alarmList(new MemberVO().withMemberId(sendMember));
        Map<String, CommentVO> commentMap = myPageService.getCommentVOList(commentVO.withMemberId(null));
        List<CommentVO> reCommentList = myPageService.getReComment(commentVO);
        System.err.println(commentMap);
        System.err.println(reCommentList);

        //코멘트, 리코멘트 맵핑
        List<CommentVO> commentList = CommentVO.sortReComment(commentMap, reCommentList);
//        commentList.sort();

        model.addAttribute("commentList", commentList);
        model.addAttribute("inputComment",new CommentVO()
                .withReciveMemberId(
                commentVO.getReciveMemberId()
        ));
        model.addAttribute("foodCode", commentVO.getFoodCode());
        //client로 알람 전달
        sseService.notify(sendMember, alarmList);
        return "content/myPage/replace/food_comment";
    }

    @Transactional
    //알람리스트 출력
    @PostMapping(value = "getAlarmPage")
    public String getAlarmPage(@RequestBody List<AlarmVO> alarmList, Model model, Principal principal){
        model.addAttribute("alarmList",alarmList);
        model.addAttribute("alarmCount",alarmService.alarmCount(new AlarmVO().withReceiveMember(principal.getName())));
        return "content/myPage/replace/alarm_content";
    }

    //댓글 삭제
    @GetMapping(value = "deleteComment")
    public String deleteComment(MemberVO memberVO,CommentVO commentVO, Principal principal,  Model model){


        return "content/myPage/replace/food_comment";
    }

    // 댓글 수정
    @ResponseBody
    @PostMapping("/updateComment")
    public ResponseEntity<String> updateComment(CommentVO commentVO){
        if(Transfer.reqexTest(commentVO.getContent())){
            return new ResponseEntity<>("!!notBlank!!", HttpStatus.BAD_REQUEST);
        }
        myPageService.updateComment(commentVO);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @GetMapping(value = "/myAlarm")
    public String myAlarm(Principal principal, MemberVO memberVO, Model model){
        String user = principal.getName();
        memberVO.setTotalDataCnt(alarmService.alarmListCnt(memberVO.withMemberId(user)));
        memberVO.setPageInfo();
        List<AlarmVO> list = alarmService.alarmList(memberVO.withMemberId(user));
        model.addAttribute("alarmList", list);

        return "content/myPage/myPage_myAlarm";
    }

//테스트
//    public static void main(String[] args) {
//        String o1 = "2024-06-09 17:20:14.111";
//        String o2 = "2024-06-10 17:20:14.111";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//
//        System.err.println(o1.equals(o2));
//        LocalDateTime a1 = LocalDateTime.parse(o1,formatter);
//        LocalDateTime a2 = LocalDateTime.parse(o2,formatter);
//        System.err.println("a1 : " + a1);
//        System.err.println("a2 : " + a2);
//        System.err.println("a2.isAfter(a1) : " + a2.isAfter(a1));
//        System.err.println("a2.isBefore(a1) : " + a2.isBefore(a1));
//    }


}
