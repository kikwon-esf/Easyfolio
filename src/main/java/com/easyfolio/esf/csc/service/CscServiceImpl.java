package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CscServiceImpl implements CscService{
    private final SqlSessionTemplate sqlSession;


    @Override
    public List<AnnVO> annList(AnnVO annVO) {
        return sqlSession.selectList("cscMapper.annList", annVO);
    }

    @Override
    public List<AnnVO> mainAnnList() {
        return sqlSession.selectList("cscMapper.mainAnnList");
    }

    @Override
    public int annCnt() {
        return sqlSession.selectOne("cscMapper.annCnt");
    }

    @Override
    public AnnVO annDetail(AnnVO annVO) {
        return sqlSession.selectOne("cscMapper.annDetail", annVO);
    }

    @Override
    public int insertAnn(AnnVO annVO) {
        return sqlSession.insert("cscMapper.insertAnn", annVO);
    }

    @Override
    public int updateAnn(AnnVO annVO) {
        return sqlSession.update("cscMapper.updateAnn", annVO);
    }

    @Override
    public int deleteAnn(AnnVO annVO) {
        return sqlSession.delete("cscMapper.deleteAnn", annVO);
    }

    @Override
    public List<InqVO> inqList() {
        return sqlSession.selectList("cscMapper.inqList");
    }

    @Override
    public InqVO inqDetail(String inqCode) {
        return sqlSession.selectOne("cscMapper.inqDetail", inqCode);
    }

    @Override
    public List<InqImgVO> inqImgList(String inqCode) {
        return sqlSession.selectList("cscMapper.inqImgList", inqCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertInq(InqVO inqVO) {
        sqlSession.insert("cscMapper.insertInq", inqVO);
        if (!inqVO.getInqImgList().isEmpty()){
            sqlSession.insert("cscMapper.insertInqImgs", inqVO);
        }
    }

    @Override
    public String nextInqCode() {
        return sqlSession.selectOne("cscMapper.nextInqCode");
    }

    @Override
    public int deleteInq(InqVO inqVO) {
        return sqlSession.delete("cscMapper.deleteInq", inqVO);
    }

    @Override
    public int deleteInqImg(InqVO inqVO) {
        return sqlSession.delete("cscMapper.deleteInqImg", inqVO);
    }

    @Override
    public int updateResponse(InqVO inqVO) {
        return sqlSession.update("cscMapper.updateResponse", inqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertResponse(ResVO resVO) {
        sqlSession.insert("cscMapper.insertResponse", resVO);
        if (!resVO.getResImgList().isEmpty()){
            sqlSession.insert("cscMapper.insertResImg", resVO);
        }
    }

    @Override
    public String nextResCode() {
        return sqlSession.selectOne("cscMapper.nextResCode");
    }

    @Override
    public List<QnaVO> mainQnaList() {
        return sqlSession.selectList("cscMapper.mainQnaList");
    }

    @Override
    public ResVO resInq(String resCode) {
        return sqlSession.selectOne("cscMapper.resInq", resCode);
    }

    @Override
    public List<ResImgVO> resImgList(String resCode) {
        return sqlSession.selectList("cscMapper.resImgList", resCode);
    }

    @Override
    public List<QnaVO> qnaList() {
        return sqlSession.selectList("cscMapper.qnaList");
    }

    @Override
    public int insertQna(QnaVO qnaVO) {
        return sqlSession.insert("cscMapper.insertQna", qnaVO);
    }


    @Override
    public List<AnnVO> allSearchAnn(String allSearchKeyword) {
        return sqlSession.selectList("cscMapper.allSearchAnn", allSearchKeyword);
    }

    @Override
    public List<QnaVO> allSearchQna(String allSearchKeyword) {
        return sqlSession.selectList("cscMapper.allSearchQna", allSearchKeyword);
    }

}
