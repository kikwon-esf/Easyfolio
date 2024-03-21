package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.*;

import java.util.List;

public interface CscService {

    public List<AnnVO> annList();

    public List<AnnVO> mainAnnList();

    public AnnVO annDetail(AnnVO annVO);

    public int insertAnn(AnnVO annVO);

    public int updateAnn(AnnVO annVO);

    public int deleteAnn(AnnVO annVO);

    public List<InqVO> inqList();

    public InqVO inqDetail(String inqCode);

    public List<InqImgVO> inqImgList(String inqCode);

    public void insertInq(InqVO inqVO);

    public String nextInqCode();

    public int deleteInq(InqVO inqVO);

    public int deleteInqImg(InqVO inqVO);

    public List<QnaVO> qnaList();


}
