package com.easyfolio.esf.member.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

@Data
public class AlarmVO extends PageVO {
    private String alarmCode;
    private String reciveMemberId;
    private String sendMemberId;
    private String foodCode;
    private String foodCommentId;
    private String alarmType;
    private String alarmCheck;
    private String content;
    private String memberName;
    private String regDate;

    public AlarmVO withReceiveMember(String user){
        this.reciveMemberId = user;
        return this;
    }
}
