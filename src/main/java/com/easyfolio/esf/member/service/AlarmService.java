package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;

import java.util.List;

public interface AlarmService {
    // 알람cnt 플러스
    public int alarmCntPlus(MemberVO memberVO);

    // 알람cnt 마이너스
    public int alarmCntMinus(MemberVO memberVO);

    // 알람 테이블 등록
    public void insertAlarm(CommentVO commentVO);

    // 알람 리스트
    public List<AlarmVO> alarmList(MemberVO memberVO);

    //알람 리스트 카운트
    public int alarmListCnt(MemberVO memberVO);

    // 알람 체크 업데이트
    public void updateAlarm(AlarmVO alarmVO);

    // 알람 모두 삭제
    public void deleteAlarmAll(MemberVO memberVO);

    // 알람 삭제
    public void deleteAlarm(AlarmVO alarmVO);

    //읽지 않은 알람 카운터
    public int alarmCount(AlarmVO alarmVO);
}
