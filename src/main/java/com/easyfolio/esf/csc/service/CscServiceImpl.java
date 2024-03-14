package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.AnnCateVO;
import com.easyfolio.esf.csc.vo.AnnVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

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
}
