package com.easyfolio.esf.otherProtocol.sse.controller;

import com.easyfolio.esf.member.service.AlarmService;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;
import com.easyfolio.esf.otherProtocol.sse.service.SseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;

@RestController
@RequestMapping(value = "/notify")
@RequiredArgsConstructor
public class SseController {

    private final SseService sseService;
    private final AlarmService alarmService;
    @GetMapping(value = "/getAlarm")
    public SseEmitter getAlarm(Principal principal){
        SseEmitter emitter = sseService.loginSSE(
                principal.getName()
                ,alarmService.alarmList(
                        new MemberVO().withMemberId(principal.getName())
                )
        );
        System.err.println("isthisworking?"+principal.getName());
        return emitter;
    }


    @PostMapping("/alarmDirty")
    @GetMapping("/alarmDirty")
    public void sendAlarmList(CommentVO commentVO){
        System.err.println(commentVO);
        alarmService.insertAlarm(commentVO);
        String sendMember = commentVO.getSendMemberId();
        sseService.notify(sendMember, alarmService.alarmList(new MemberVO().withMemberId(sendMember)));
    }

}
