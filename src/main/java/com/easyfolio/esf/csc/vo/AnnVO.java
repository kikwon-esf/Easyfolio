package com.easyfolio.esf.csc.vo;

import lombok.Data;

@Data
public class AnnVO extends PageVO{
    private String annNum;
    private String annCate;
    private String annTitle;
    private String annWriter;
    private String annContent;
    private String annDate;
    private AnnCateVO annCateList;
}
