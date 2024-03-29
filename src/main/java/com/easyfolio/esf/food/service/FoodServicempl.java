package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServicempl implements FoodService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<FoodVO> allFoodList(FoodVO foodVO) {
        return sqlSession.selectList("foodMapper.allFoodList", foodVO);
    }

    @Override
    public List<FoodKindVO> foodKindList() {
        return sqlSession.selectList("foodMapper.foodKindList");
    }

    @Override
    public FoodKindVO foodKindText(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.foodKindText", foodVO);
    }

    @Override
    public List<FoodUsageVO> foodUsageList() {
        return sqlSession.selectList("foodMapper.foodUsageList");
    }

    @Override
    public FoodUsageVO foodUsageText(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.foodUsageText", foodVO);
    }

    @Override
    public List<FoodMtrlVO> foodMtrlList() {
        return sqlSession.selectList("foodMapper.foodMtrlList");
    }

    @Override
    public FoodMtrlVO foodMtrlText(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.foodMtrlText", foodVO);
    }

    // 방법별
    @Override
    public List<FoodTypeVO> foodTypeList() {
        return sqlSession.selectList("foodMapper.foodTypeList");
    }

    @Override
    public FoodTypeVO foodTypeText(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.foodTypeText", foodVO);
    }

    @Override
    public int foodCnt() {
        return sqlSession.selectOne("foodMapper.foodCnt");
    }


    @Override
    public List<FoodVO> searchFoodAll(FoodVO foodVO) {
        return sqlSession.selectList("foodMapper.searchFoodAll", foodVO);
    }

    @Override
    public List<FoodVO> allSearchFood(String allSearchKeyword) {
        return sqlSession.selectList("foodMapper.allSearchFood", allSearchKeyword);
    }

    @Override
    public int allSearchFoodCnt(String allSearchKeyword) {
        return sqlSession.selectOne("foodMapper.allSearchFoodCnt", allSearchKeyword);
    }
    //food상세검색
    @Override
    public FoodVO getFoodDtl(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.getFoodDtl",foodVO);
    }

    @Override
    public int searchFoodCnt(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.searchFoodCnt", foodVO);
    }

//

}
