package com.easyfolio.esf.csc.vo.ann;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

@Data
public class AnnVO extends PageVO {
    private String annCode;
    private String annCate;
    private String annTitle;
    private String annWriter;
    private String annContent;
    private String annDate;
    private AnnCateVO annCateList;
    private String allSearchKeyword;
}
