package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.AnnCateVO;
import com.easyfolio.esf.csc.vo.AnnVO;

import java.util.List;

public interface CscService {

    // 고객센터 메인 페이지 공지사항 목록 조회
    public List<AnnVO> annListOffset();

    // 공지사항 목록 조회
    public List<AnnVO> annList(AnnVO annVO);

    // 공지사항 목록 추가
    public int insertAnn(AnnVO annVO);

    // 공지사항 게시물 총 개수 조회
    public int selectAnnCnt(String annCate);

    // 공지사항 상세 조회
    public AnnVO selectAnnDetail(AnnVO annVO);

    // 공지사항 메뉴 목록 조회
    public List<AnnCateVO> annCateList(AnnCateVO annCateVO);

    // 공지사항 수정
    public int updateAnn(AnnVO annVO);

    // 공지사항 삭제
    public int deleteAnn(AnnVO annVO);

}
