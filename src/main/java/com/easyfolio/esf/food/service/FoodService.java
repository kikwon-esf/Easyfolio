package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.*;

import java.util.List;

public interface FoodService {
    public List<FoodVO> allFoodList(FoodVO foodVO); // 음식 전체 목록
    // 음식 카테고리
    public List<FoodKindVO> foodKindList();
    public FoodKindVO foodKindText(FoodVO foodVO);

    // 음식 카테고리2
    public List<FoodUsageVO> foodUsageList();
    public FoodUsageVO foodUsageText(FoodVO foodVO);

    // 음식 카테고리3
    public List<FoodMtrlVO> foodMtrlList();
    public FoodMtrlVO foodMtrlText(FoodVO foodVO);

    // 음식 방법별
    public List<FoodTypeVO> foodTypeList();
    public FoodTypeVO foodTypeText(FoodVO foodVO);


    public int foodCnt();

    public int searchFoodCnt(FoodVO foodVO);
//    public FoodVO foodDetail(FoodVO foodVO); // 음식 상세정보
    public List<FoodVO> searchFoodAll(FoodVO foodVO); // 음식 이름 검색

    // 헤더 검색 음식
    public List<FoodVO> allSearchFood(String allSearchKeyword);

    public int allSearchFoodCnt(String allSearchKeyword);

    public FoodVO getFoodDtl(FoodVO foodVO);

    public FoodVO selectFoodCode(FoodVO foodVO);
}
