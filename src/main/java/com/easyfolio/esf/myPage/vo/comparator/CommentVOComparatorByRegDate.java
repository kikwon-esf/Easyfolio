package com.easyfolio.esf.myPage.vo.comparator;

import com.easyfolio.esf.myPage.vo.CommentVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class CommentVOComparatorByRegDate implements Comparator<CommentVO> {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static CommentVOComparatorByRegDate comparator = new CommentVOComparatorByRegDate();

    @Override
    public int compare(CommentVO o1, CommentVO o2) {
        LocalDateTime d1 = LocalDateTime.parse(o1.getReg_date(), formatter);
        LocalDateTime d2 = LocalDateTime.parse(o2.getReg_date(), formatter);

        if(d1.isAfter(d2)){
            return 1;
        }else if(d1.isBefore(d2)){
            return -1;
        }else{
            return 0;
        }
    }

    private CommentVOComparatorByRegDate(){

    }

    public static CommentVOComparatorByRegDate getCommentVOComparatorByRegDate(){
        return comparator;
    }
}
