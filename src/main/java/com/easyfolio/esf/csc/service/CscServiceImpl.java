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
    public List<AnnVO> annList() {
        return sqlSession.selectList("cscMapper.annList");
    }

    @Override
    public List<AnnVO> mainAnnList() {
        return sqlSession.selectList("cscMapper.mainAnnList");
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
    public List<QnaVO> qnaList() {
        return sqlSession.selectList("cscMapper.qnaList");
    }

}
