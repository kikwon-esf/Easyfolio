package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;
import com.easyfolio.esf.otherProtocol.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private final SqlSessionTemplate sqlSession;

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

    @Override
    public void updateAlarm(AlarmVO alarmVO) {
        sqlSession.update("memberMapper.updateAlarm", alarmVO);
    }

    // 알람 모두 삭제
    @Override
    public void deleteAlarmAll(MemberVO memberVO) {
        sqlSession.delete("memberMapper.deleteAlarmAll", memberVO);
    }

    // 알람 삭제
    @Override
    public void deleteAlarm(AlarmVO alarmVO) {
        sqlSession.delete("memberMapper.deleteAlarm", alarmVO);

    }

    @Override
    public int alarmCount(AlarmVO alarmVO) {
        return sqlSession.selectOne("memberMapper.alarmCount", alarmVO);
    }


}
