package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.FoodVO;

import java.util.List;

public interface FoodService {
    public List<FoodVO> foodList(); // 음식 전체 목록
    public List<FoodVO> foodNameList(FoodVO foodVO); // 음식명
    public List<FoodVO> foodTypeList(FoodVO foodVO); // 음식 타입
    public List<FoodVO> foodMTRLList(FoodVO foodVO); // 음식 재료
    public List<FoodVO> foodKindList(FoodVO foodVO); // 음식 종류
    public List<FoodVO> foodServeList(FoodVO foodVO); // 음식양
    public List<FoodVO> foodDifficultList(FoodVO foodVO); // 음식조리난이도
    public List<FoodVO> foodTimeList(FoodVO foodVO); // 음식조리시간
    public List<FoodVO> foodREGDTList(FoodVO foodVO); // 음식등록일
    public List<FoodVO> foodUsageList(FoodVO foodVO); // 음식용도
    public FoodVO foodDetail(FoodVO foodVO); // 음식 상세 조회

    public int insertFood(FoodVO foodVO); // 음식 등록
    public int updateFood(FoodVO foodVO); // 음식 수정
    public int deleteFood(FoodVO foodVO); // 음식 삭제
}
