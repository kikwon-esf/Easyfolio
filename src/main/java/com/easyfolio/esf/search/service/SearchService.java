package com.easyfolio.esf.search.service;

import com.easyfolio.esf.search.vo.SearchVO;

import java.util.List;
public interface SearchService {
    public List<SearchVO> search(String search);

    public List<SearchVO> searchFood(String search);
}
