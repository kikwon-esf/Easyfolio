package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.*;

import java.util.List;

public interface CscService {

    // ANN

    public List<AnnVO> annList(AnnVO annVO);

    public List<AnnVO> mainAnnList();

    public int annCnt();

    public AnnVO annDetail(AnnVO annVO);

    public int insertAnn(AnnVO annVO);

    public int updateAnn(AnnVO annVO);

    public int deleteAnn(AnnVO annVO);

    // INQ

    public List<InqVO> inqList();

    public InqVO inqDetail(String inqCode);

    public List<InqImgVO> inqImgList(String inqCode);

    public void insertInq(InqVO inqVO);

    public String nextInqCode();

    public int deleteInq(InqVO inqVO);

    public int deleteInqImg(InqVO inqVO);

    public ResVO resInq(String resCode);

    public List<ResImgVO> resImgList(String resCode);

    public int updateResponse(InqVO inqVO);

    public void insertResponse(ResVO resVO);

    public String nextResCode();




    // QNA

    public List<QnaVO> qnaList();

    public int insertQna(QnaVO qnaVO);




    // 헤더 검색 공지사항
    public List<AnnVO> allSearchAnn(String allSearchKeyword);

    // 헤더 검색 QNA
    public List<QnaVO> allSearchQna(String allSearchKeyword);


}
