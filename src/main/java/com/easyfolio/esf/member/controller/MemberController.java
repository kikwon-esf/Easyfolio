package com.easyfolio.esf.member.controller;

import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    @Resource
    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    //로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(Principal principal){
        if(principal != null && principal.getName() !=null){
            return "redirect:/";
        }
        return "content/member/login";
    }

    //로그인 에러
    @GetMapping("/loginForm/error")
    public String loginForm(Model model){
        model.addAttribute("loginError","아이디와 비밀번호를 확인해주세요.");
        return "content/member/login";
    }
    @GetMapping("/joinForm")
    public String joinForm(){

        return "content/member/join";
    }
    @GetMapping("/findMemberForm")
    public String findMemberForm(){

        return "content/member/find_member";
    }
    // 아이디 중복 체크
    @ResponseBody
    @PostMapping("/checkId")
    public boolean checkId(@RequestBody Map<String, String> requestBody) {
        String memberId = requestBody.get("memberId");
        return memberService.checkId(memberId);
    }

    // 회원가입
    @PostMapping("/joinMember")
    public String joinMember(MemberVO memberVo){
        memberVo.setMemberPw(passwordEncoder.encode(memberVo.getMemberPw()));
        memberService.join(memberVo);
        return "redirect:/member/loginForm";
    }

    // 아이디 찾기
    @ResponseBody
    @PostMapping("/findId")
    public List<MemberVO> findId(MemberVO memberVO) {

        List<MemberVO> members = memberService.findId(memberVO);
        System.out.println(members);
        return members;
    }

    // 비밀번호 찾기
    @ResponseBody
    @PostMapping("/findPw")
    public List<MemberVO> findPw(MemberVO memberVO) {
        System.out.println(memberVO);
        List<MemberVO> members = memberService.findPw(memberVO);
        System.out.println(members);
        return members;
    }


}
