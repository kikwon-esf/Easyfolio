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


    // 고객센터 메인 페이지 공지사항 목록 조회
    @Override
    public List<AnnVO> annListOffset() {
        return sqlSession.selectList("cscMappper.annListOffset");
    }

    // 공지사항 목록 조회
    @Override
    public List<AnnVO> annList(AnnVO annVO) {
        return null;
    }

    // 공지사항 목록 추가
    @Override
    public int insertAnn(AnnVO annVO) {
        return 0;
    }

    // 공지사항 게시물 총 개수 조회
    @Override
    public int selectAnnCnt(String annCate) {
        return 0;
    }

    // 공지사항 상세 조회
    @Override
    public AnnVO selectAnnDetail(AnnVO annVO) {
        return null;
    }

    // 공지사항 메뉴 목록 조회
    @Override
    public List<AnnCateVO> annCateList(AnnCateVO annCateVO) {
        return null;
    }

    // 공지사항 수정
    @Override
    public int updateAnn(AnnVO annVO) {
        return 0;
    }

    // 공지사항 삭제
    @Override
    public int deleteAnn(AnnVO annVO) {
        return 0;
    }
}
