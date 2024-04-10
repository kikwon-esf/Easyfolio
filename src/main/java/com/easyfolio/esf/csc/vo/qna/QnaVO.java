package com.easyfolio.esf.csc.vo.qna;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

@Data
public class QnaVO extends PageVO {
    public String qnaCode;
    public String qnaQuestion;
    public String qnaAnswer;
    private String allSearchKeyword;
}
