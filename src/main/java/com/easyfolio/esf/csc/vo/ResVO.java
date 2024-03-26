package com.easyfolio.esf.csc.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResVO {
    public String resCode;
    public String inqCode;
    public String resContent;
    public String resDate;
    private List<ResImgVO> resImgList;
}
