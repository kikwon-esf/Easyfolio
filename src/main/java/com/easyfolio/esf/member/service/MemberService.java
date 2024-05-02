package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;

import java.util.List;

public interface MemberService {
    // 아이디 중복 체크
    public boolean checkId(String memberId);

    // 회원가입
    public int join(MemberVO memberVO);

    // 아이디 찾기
    public List<MemberVO> findId(MemberVO memberVO);

    // 비밀번호 찾기
    public List<MemberVO> findPw(MemberVO memberVO);

    // 회원찾기
    public MemberVO findMemberById(String id);

    // 알람cnt 플러스
    public int alarmCntPlus(MemberVO memberVO);

    // 알람cnt 마이너스
    public int alarmCntMinus(MemberVO memberVO);

    // 알람 테이블 등록
    public void insertAlarm(CommentVO commentVO);

    // 알람 리스트
    public List<AlarmVO> alarmList(MemberVO memberVO);
}
