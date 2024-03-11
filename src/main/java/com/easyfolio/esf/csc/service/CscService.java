package com.easyfolio.esf.csc.service;

import com.easyfolio.esf.csc.vo.AnnCateVO;
import com.easyfolio.esf.csc.vo.AnnVO;

import java.util.List;

public interface CscService {

    public List<AnnVO> annList();

    public List<AnnVO> mainAnnList();

    public AnnVO annDetail(AnnVO annVO);

    public int insertAnn(AnnVO annVO);

}
