package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SqlSessionTemplate sqlSession;
    // 아이디 중복 체크
    @Override
    public boolean checkId(String memberId) {
        return sqlSession.selectOne("memberMapper.checkId", memberId) == null;
    }

    // 회원가입
    @Override
    public int join(MemberVO memberVO){
        return sqlSession.insert("memberMapper.join", memberVO);
    }
}
