package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SqlSessionTemplate sqlSession;
    private final PasswordEncoder passwordEncoder;
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

    // 아이디 찾기
    @Override
    public List<MemberVO> findId(MemberVO memberVO) {
        return sqlSession.selectList("memberMapper.findId", memberVO);
    }

    // 비밀번호 찾기
    @Override
    public List<MemberVO> findPw(MemberVO memberVO) {
        return sqlSession.selectList("memberMapper.findPw", memberVO);
    }

    @Override
    public MemberVO findMemberById(String memberId) {
        return sqlSession.selectOne("memberMapper.login",memberId);
    }

    @Override
    public int alarmCntPlus(MemberVO memberVO) {
        return sqlSession.update("memberMapper.alarmCntPlus", memberVO);
    }

    @Override
    public int alarmCntMinus(MemberVO memberVO) {
        return sqlSession.update("memberMapper.alarmCntMinus", memberVO);
    }

    @Override
    public void insertAlarm(CommentVO commentVO) {
        sqlSession.insert("memberMapper.insertAlarm", commentVO);
    }

    @Override
    public List<AlarmVO> alarmList(MemberVO memberVO) {
        return sqlSession.selectList("memberMapper.alarmList", memberVO);
    }


}
