package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.*;
import com.easyfolio.esf.csc.vo.ann.AnnCateVO;
import com.easyfolio.esf.csc.vo.ann.AnnVO;
import com.easyfolio.esf.csc.vo.inq.InqImgVO;
import com.easyfolio.esf.csc.vo.inq.InqVO;
import com.easyfolio.esf.csc.vo.qna.QnaVO;

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

    public List<AnnVO> cscSearchAnn(AnnVO annVO);

    public List<AnnCateVO> annCateList(AnnCateVO annCateVO);

    public int cscSearchAnnCnt(AnnVO annVO);

    // INQ

    public List<InqVO> inqList();

    public InqVO inqDetail(InqVO inqVO);

    public List<InqImgVO> inqImgList(InqVO inqVO);

    public void insertInq(InqVO inqVO);

    public String nextInqCode();

    public int deleteInq(InqVO inqVO);

    public int deleteInqImg(InqVO inqVO);

    public ResVO resInq(ResVO resVO);

    public List<ResImgVO> resImgList(ResVO resVO);

    public int updateResponse(InqVO inqVO);

    public void insertResponse(ResVO resVO);

    public String nextResCode();




    // QNA

    public List<QnaVO> mainQnaList();

    public List<QnaVO> qnaList(QnaVO qnaVO);

    public int insertQna(QnaVO qnaVO);

    public int qnaCnt();

    public List<QnaVO> cscSearchQna(QnaVO qnaVO);

    public int cscSearchQnaCnt (QnaVO qnaVO);

    public int deleteQna (QnaVO qnaVO);

    public int updateQna(QnaVO qnaVO);




    // 헤더 검색 공지사항
    public List<AnnVO> allSearchAnn(String allSearchKeyword);

    // 헤더 검색 QNA
    public List<QnaVO> allSearchQna(String allSearchKeyword);


}
