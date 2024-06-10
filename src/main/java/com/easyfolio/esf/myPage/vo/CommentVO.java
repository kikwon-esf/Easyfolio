package com.easyfolio.esf.myPage.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.comparator.CommentVOComparatorByRegDate;
import lombok.Data;

import java.lang.reflect.Member;
import java.util.*;

@Data
public class CommentVO extends PageVO {
    private MemberVO memberVO;
    private FoodVO foodVO;
    private String foodCommentId;
    private String foodCode;
    private String memberId;
    private String reciveMemberId;
    private String sendMemberId;
    private String reg_date;
    private String content;
    private String reCode;
    private List<CommentVO> reCommentList;

    public CommentVO withMemberId(String memberId){
        this.memberId = memberId;
        return this;
    }
    public CommentVO withReciveMemberId(String memberId){
        this.reciveMemberId = memberId;
        return this;
    }

    public CommentVO withFoodCode(String foodCode){
        this.foodCode = foodCode;
        return this;
    }

    public static List<CommentVO> sortReComment(Map<String, CommentVO> commentMap, List<CommentVO> reCommentList){

        //reComment들을 comment의 reCommentList에 맵핑
        Iterator<CommentVO> it = reCommentList.listIterator();
        while (it.hasNext()){
            CommentVO each = it.next();
            commentMap.get(each.getReCode()).addReCommentList(each);
        }
        //Map으로 가져온 녀석들을 List형식으로 반환
        Set<Map.Entry<String, CommentVO>> commentEntry = commentMap.entrySet();
        Iterator<Map.Entry<String, CommentVO>> commentIt = commentEntry.iterator();
        List<CommentVO> resultList = new ArrayList<>();
        while (commentIt.hasNext()){
            resultList.add(commentIt.next().getValue());
        }
        resultList.sort(CommentVOComparatorByRegDate.getCommentVOComparatorByRegDate());
        return resultList;
    }

    public void addReCommentList(CommentVO commentVO){
        if(this.reCommentList != null){
            this.reCommentList.add(commentVO);
        }else{
            this.reCommentList = new ArrayList<CommentVO>();
            this.reCommentList.add(commentVO);
        }
    }


}
