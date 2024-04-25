package com.easyfolio.esf.myPage.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.vo.MemberVO;
import lombok.Data;

import java.lang.reflect.Member;

@Data
public class CommentVO extends PageVO {
    private MemberVO memberVO;
    private FoodVO foodVO;
    private String foodCommentId;
    private String foodCode;
    private String memberId;
    private String reg_date;
    private String content;
}
