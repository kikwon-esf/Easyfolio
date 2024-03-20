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
    public List<FoodVO> allFoodList() {
        return sqlSession.selectList("foodMapper.allFoodList");
    }

    // 음식 전체 목록 조회


}
