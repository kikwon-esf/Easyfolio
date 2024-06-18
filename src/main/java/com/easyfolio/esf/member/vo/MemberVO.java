package com.easyfolio.esf.member.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

import java.lang.reflect.Member;

@Data
public class MemberVO extends PageVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberRole;
    private String alamCnt;
    private String nickName;

    public MemberVO withMemberId(String memberId){
        this.memberId = memberId;
        return this;
    }
}
