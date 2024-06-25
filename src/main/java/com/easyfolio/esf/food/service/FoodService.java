package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.*;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.weather.vo.DdabongVO;

import java.util.List;

public interface FoodService {
    public List<FoodVO> allFoodList(FoodVO foodVO);// 음식 전체 목록

    public List<FoodVO> allRecipeList();
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

    public void updateFoodInqCnt(FoodVO foodVO);

    public List<FoodStepsVO> getFoodSteps(FoodVO foodVO);

    public List<FoodVO> ddabongRecipeList(List<String> foodNames);

    public int ddabongRecipeCount(List<String> foodNames);

    public int allRecipeCount();

    public String nextFoodCode();

    public void insertFood(FoodVO foodVO, FoodStepsVO foodStepsVO, FoodImgVO foodImgVO);

    public FoodImgVO selectFoodImg(FoodVO foodVO);

    public void updateFood(FoodVO foodVO, FoodStepsVO foodStepsVO, FoodImgVO foodImgVO);

    public void updateFood(FoodVO foodVO, FoodStepsVO foodStepsVO);

    public void updateAndInsertImg(FoodVO foodVO, FoodStepsVO foodStepsVO, FoodImgVO foodImgVO);

    public List<FoodVO> selectRecentView(String memberId);

    public List<FoodVO> myRecentView(FoodVO foodVO);

    public int myRecentViewCnt(FoodVO foodVO);

    public void insertRecentView(FoodVO foodVO);

    public void deleteFood(FoodVO foodVO);
}
