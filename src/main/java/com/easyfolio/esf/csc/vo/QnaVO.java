package com.easyfolio.esf.csc.vo;

import lombok.Data;

@Data
public class QnaVO extends PageVO{
    public String qnaCode;
    public String qnaQuestion;
    public String qnaAnswer;
    private String allSearchKeyword;
}
