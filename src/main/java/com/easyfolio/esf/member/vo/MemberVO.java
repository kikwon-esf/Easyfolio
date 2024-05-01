package com.easyfolio.esf.member.vo;

import lombok.Data;

import java.lang.reflect.Member;

@Data
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberRole;
    private String alamCnt;

    public MemberVO withMemberId(String memberId){
        this.memberId = memberId;
        return this;
    }
}
