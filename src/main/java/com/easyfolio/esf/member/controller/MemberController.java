package com.easyfolio.esf.member.controller;

import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    @Resource
    private final MemberService memberService;

    @GetMapping("/loginForm")
    public String loginForm(){

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
