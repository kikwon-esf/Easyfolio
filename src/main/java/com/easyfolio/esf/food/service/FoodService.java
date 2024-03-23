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
//    public List<FoodVO> foodTypeList(String foodType); // 음식 타입 검색
//    public List<FoodVO> foodMtrlList(String foodMtrl); // 음식 재료 검색
//    public List<FoodVO> foodUsageList(String foodUsage); // 음식 용도 검색
//    public List<FoodVO> foodKindList(String foodKind); // 음식 종류 검색
//    public List<FoodVO> foodDifList(String foodDif); // 음식 난이도 검색
//    public List<FoodVO> foodServeList(String foodServe); // 음식 양 검색
//    public List<FoodVO> foodTimeList(String foodTime); // 음식 시간 검색
//    public List<FoodVO> foodRegDtList(String foodRegDt); // 음식 등록일 검색
//    public int insertFood(FoodVO foodVO); // 음식 등록
//    public int updateFood(FoodVO foodVO); // 음식 수정
//    public int deleteFood(FoodVO foodVO); // 음식 삭제
//    public int updateFoodInqCnt(FoodVO foodVO); // 음식 조회수 증가
//    public int updateFoodRcmmCnt(FoodVO foodVO); // 음식 추천수 증가
//    public List<FoodVO> foodRcmmCntList(String foodRcmmCnt); // 추천음식 목록
//    public List<FoodVO> foodInqCntList(String foodInqCnt); // 조회수 높은 음식 목록
//    public int updateFoodRcmmCntDown(FoodVO foodVO); // 음식 추천수 감소
//    public int updateFoodInqCntDown(FoodVO foodVO); // 음식 조회수 감소

}
