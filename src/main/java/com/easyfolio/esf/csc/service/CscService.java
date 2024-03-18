package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.AnnCateVO;
import com.easyfolio.esf.csc.vo.AnnVO;
import com.easyfolio.esf.csc.vo.InqImgVO;
import com.easyfolio.esf.csc.vo.InqVO;

import java.util.List;

public interface CscService {

    public List<AnnVO> annList();

    public List<AnnVO> mainAnnList();

    public AnnVO annDetail(AnnVO annVO);

    public int insertAnn(AnnVO annVO);

    public int updateAnn(AnnVO annVO);

    public int deleteAnn(AnnVO annVO);

    public List<InqVO> inqList();

    public InqVO inqDetail(InqVO inqVO);

    public List<InqImgVO> inqImgList(String inqCode);

    public void insertInq(InqVO inqVO);

    public String nextInqCode();


}
