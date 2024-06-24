package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.*;
import com.easyfolio.esf.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<FoodVO> allFoodList(FoodVO foodVO) {
        return sqlSession.selectList("foodMapper.allFoodList", foodVO);
    }

    @Override
    public List<FoodVO> allRecipeList() {
        return sqlSession.selectList("foodMapper.allRecipeList");
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
    public void insertRecentView(FoodVO foodVO){
        sqlSession.insert("foodMapper.insertRecentView", foodVO);
    }

    @Override
    public FoodVO selectFoodCode(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.selectFoodCode",foodVO);
    }

    @Override
    public void updateFoodInqCnt(FoodVO foodVO) {
        sqlSession.update("foodMapper.updateFoodInqCnt", foodVO);
    }

    @Override
    public int searchFoodCnt(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.searchFoodCnt", foodVO);
    }


    @Override
    public List<FoodStepsVO> getFoodSteps(FoodVO foodVO) {
        return sqlSession.selectList("foodMapper.getFoodSteps", foodVO);
    }

    @Override
    public List<FoodVO> ddabongRecipeList(List<String> foodNames) {
        return sqlSession.selectList("foodMapper.ddabongRecipeList", foodNames);
    }

    @Override
    public int ddabongRecipeCount(List<String> foodNames) {
        return sqlSession.selectOne("foodMapper.ddabongRecipeCount",foodNames);
    }

    @Override
    public int allRecipeCount() {
        return sqlSession.selectOne("foodMapper.allRecipeCount");
    }

    @Override
    public String nextFoodCode() {
        return sqlSession.selectOne("foodMapper.nextFoodCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFood(FoodVO foodVO, FoodStepsVO foodStepsVO, FoodImgVO foodImgVO) {
        sqlSession.insert("foodMapper.insertFood", foodVO);
        sqlSession.insert("foodMapper.insertFoodSteps", foodStepsVO);
        sqlSession.insert("foodMapper.insertFoodImg", foodImgVO);
    }

    @Override
    public FoodImgVO selectFoodImg(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.selectFoodImg", foodVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFood(FoodVO foodVO, FoodStepsVO foodStepsVO, FoodImgVO foodImgVO) {
        sqlSession.update("foodMapper.updateFood", foodVO);
        sqlSession.update("foodMapper.updateFoodSteps", foodStepsVO);
        sqlSession.update("foodMapper.updateFoodImg", foodImgVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFood(FoodVO foodVO, FoodStepsVO foodStepsVO) {
        sqlSession.update("foodMapper.updateFood", foodVO);
        sqlSession.update("foodMapper.updateFoodSteps", foodStepsVO);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAndInsertImg(FoodVO foodVO, FoodStepsVO foodStepsVO, FoodImgVO foodImgVO) {
        sqlSession.update("foodMapper.updateFood", foodVO);
        sqlSession.update("foodMapper.updateFoodSteps", foodStepsVO);
        sqlSession.insert("foodMapper.insertFoodImg", foodImgVO);
    }

    @Override
    public List<FoodVO> selectRecentView(String memberId) {
        return sqlSession.selectList("foodMapper.selectRecentView", memberId);
    }

    @Override
    public List<FoodVO> myRecentView(String memberId) {
        return sqlSession.selectList("foodMapper.myRecentView", memberId);
    }


//

}
