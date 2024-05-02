package com.easyfolio.esf.member.vo;

import lombok.Data;

@Data
public class AlarmVO {
    private String alarmCode;
    private String reciveMemberId;
    private String sendMemberId;
    private String foodCode;
    private String foodCommentId;
    private String alarmType;
    private String content;
    private String nickName;
    private String regDate;
}
