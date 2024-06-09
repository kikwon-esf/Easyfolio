package com.easyfolio.esf.member.controller;

import com.easyfolio.esf.config.interceptor.CreateSessionInterceptor;
import com.easyfolio.esf.member.service.AlarmService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.otherProtocol.sse.service.SseService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final AlarmService alarmService;
    private final SseService sseService;
    private final PasswordEncoder passwordEncoder;

    //로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(Principal principal, HttpServletRequest request){
        if(principal != null && principal.getName() !=null){
            return "redirect:/";
        }
        return "content/member/login";
    }

    //로그인 에러
    @GetMapping("/loginForm/error")
    public String loginForm(Model model, Principal principal){
        if(principal != null && principal.getName() !=null){
            return "redirect:/";
        }
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
        return members;
    }

    // 비밀번호 찾기
    @ResponseBody
    @PostMapping("/findPw")
    public List<MemberVO> findPw(MemberVO memberVO) {
        List<MemberVO> members = memberService.findPw(memberVO);
        return members;
    }

    // 알림창에서 이동
    @GetMapping("/alarmDetail")
    public String alarmDetail(AlarmVO alarmVO){
        alarmService.updateAlarm(alarmVO);
        return "redirect:/food/detail?foodCode=" + alarmVO.getFoodCode();
    }

    // 알람 모두 삭제
    @ResponseBody
    @Transactional
    @PostMapping("/deleteAlarmAll")
    public void deleteAlarmAll(Principal principal, MemberVO memberVO) {
        String user = principal.getName();
        memberVO.setMemberId(user);
        alarmService.deleteAlarmAll(memberVO);
        sseService.notify(principal.getName(),alarmService.alarmList(memberVO));
        sseService.notify(user, alarmService.alarmList(new MemberVO().withMemberId(user)));

    }

    // 알람 삭제
    @ResponseBody
    @PostMapping("/deleteAlarm")
    public void deleteAlarm(AlarmVO alarmVO, Principal principal) {
        alarmService.deleteAlarm(alarmVO);
        String user = principal.getName();
        sseService.notify(user, alarmService.alarmList(new MemberVO().withMemberId(user)));

    }

}
