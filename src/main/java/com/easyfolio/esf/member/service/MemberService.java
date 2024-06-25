package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;

import java.security.PublicKey;
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

    //회원정보 수정
    public int updateMember(MemberVO memberVO);

    public MemberVO selectMemberName(String memberId);

    public int deleteMember(MemberVO memberVO);
}
