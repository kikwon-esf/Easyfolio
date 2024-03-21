package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.FoodVO;
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
    public int foodCnt(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.foodCnt", foodVO);
    }

//    // 음식 상세정보
//    @Override
//    public FoodVO foodDetail(FoodVO foodVO) {
//        return sqlSession.selectOne("foodMapper.foodDetail", foodVO);
//    }
//
//    // 음식명 검색
//    @Override
//    public List<FoodVO> FoodNameList(String foodName) {
//        return sqlSession.selectList("foodMapper.FoodNameList", foodName);
//    }
//
//    // 음식 타입 검색
//    @Override
//    public List<FoodVO> foodTypeList(String foodType) {
//        return sqlSession.selectList("foodMapper.foodTypeList", foodType);
//    }
//
//    // 음식 재료별 검색
//    @Override
//    public List<FoodVO> foodMtrlList(String foodMtrlCn) {
//        return sqlSession.selectList("foodMapper.foodMtrlList", foodMtrlCn);
//    }
//
//    // 음식 용도별 검색
//    @Override
//    public List<FoodVO> foodUsageList(String foodUsage) {
//        return sqlSession.selectList("foodMapper.foodUsageList", foodUsage);
//    }
//
//    // 음식 종류별 검색
//    @Override
//    public List<FoodVO> foodKindList(String foodKind) {
//        return sqlSession.selectList("foodMapper.foodKindList", foodKind);
//    }
//
//    // 음식 난이도별 검색
//    @Override
//    public List<FoodVO> foodDifList(String foodDif) {
//        return sqlSession.selectList("foodMapper.foodDifList", foodDif);
//    }
//
//    //음식 양별 검색
//    @Override
//    public List<FoodVO> foodServeList(String foodServe) {
//        return sqlSession.selectList("foodMapper.foodServeList", foodServe);
//    }
//
//    // 음식 시간별 검색
//    @Override
//    public List<FoodVO> foodTimeList(String foodTime) {
//        return sqlSession.selectList("foodMapper.foodTimeList", foodTime);
//    }
//
//    // 음식 등록일별 검색
//    @Override
//    public List<FoodVO> foodRegDtList(String foodRegDt) {
//        return sqlSession.selectList("foodMapper.foodRegDtList", foodRegDt);
//    }
//
//    // 음식 등록
//    @Override
//    public int insertFood(FoodVO foodVO) {
//        return sqlSession.insert("foodMapper.insertFood", foodVO);
//    }
//
//    // 음식 수정
//    @Override
//    public int updateFood(FoodVO foodVO) {
//        return sqlSession.update("foodMapper.updateFood", foodVO);
//    }
//
//    // 음식 삭제
//    @Override
//    public int deleteFood(FoodVO foodVO) {
//        return sqlSession.delete("foodMapper.deleteFood", foodVO);
//    }
//
//    // 음식 조회수 증가
//    @Override
//    public int updateFoodInqCnt(FoodVO foodVO) {
//        return sqlSession.update("foodMapper.updateFoodInqCnt", foodVO);
//    }
//
//    // 음식 조회수 감소
//    @Override
//    public int updateFoodInqCntDown(FoodVO foodVO) {
//        return sqlSession.update("foodMapper.updateFoodInqCntDown", foodVO);
//    }
//
//    // 음식 추천수 증가
//    @Override
//    public int updateFoodRcmmCnt(FoodVO foodVO) {
//        return sqlSession.update("foodMapper.updateFoodRcmmCnt", foodVO);
//    }
//
//    // 추천수별 음식 목록
//    @Override
//    public List<FoodVO> foodRcmmCntList(String foodRcmmCnt) {
//        return sqlSession.selectList("foodMapper.foodRcmmCntList", foodRcmmCnt);
//    }
//
//    // 조회수별 음식 목록
//    @Override
//    public List<FoodVO> foodInqCntList(String foodInqCnt) {
//        return sqlSession.selectList("foodMapper.foodInqCntList", foodInqCnt);
//    }
//
//    // 음식 추천수 감소
//    @Override
//    public int updateFoodRcmmCntDown(FoodVO foodVO) {
//        return sqlSession.update("foodMapper.updateFoodRcmmCntDown", foodVO);
//    }

}
