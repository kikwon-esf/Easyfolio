package com.easyfolio.esf.food.service;

import com.easyfolio.esf.food.vo.FoodVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServicempl {
    private final SqlSessionTemplate sqlSession;

    // 음식 전체 목록 조회
    public List<FoodVO> foodList() {
        return sqlSession.selectList("foodMapper.foodList");
    }
    // 음식 상세 조회
    public FoodVO foodDetail(FoodVO foodVO) {
        return sqlSession.selectOne("foodMapper.foodDetail", foodVO);
    }

    // 음식명 조회
    public List<FoodVO> foodNameList(FoodVO foodVO) {
        return sqlSession.selectList("foodMapper.foodNameList", foodVO);
    }

    // 음식 타입 조회
    public List<FoodVO> foodTypeList(FoodVO foodVO) {
        return sqlSession.selectList("foodMapper.foodTypeList", foodVO);
    }

    // 음식 등록
    public int insertFood(FoodVO foodVO) {
        return sqlSession.insert("foodMapper.insertFood", foodVO);
    }

    // 음식 수정
    public int updateFood(FoodVO foodVO) {
        return sqlSession.update("foodMapper.updateFood", foodVO);
    }

    // 음식 삭제
    public int deleteFood(FoodVO foodVO) {
        return sqlSession.delete("foodMapper.deleteFood", foodVO);
    }

}
