package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.MemberVO;

public interface MemberService {
    // 아이디 중복 체크
    public boolean checkId(String memberId);
    // 회원가입
    public int join(MemberVO memberVO);
}
