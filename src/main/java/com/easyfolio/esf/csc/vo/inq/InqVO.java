package com.easyfolio.esf.csc.vo.inq;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

import java.util.List;

@Data
public class InqVO extends PageVO {
    public String inqCode;
    public String inqCate;
    public String inqWriter;
    public String inqTitle;
    public String inqContent;
    public String inqDate;
    public String inqResponse;
    public InqCateVO inqCateList;
    private List<InqImgVO> inqImgList;


}
