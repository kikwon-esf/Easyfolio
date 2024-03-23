package com.easyfolio.esf.myPage.service;

import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<FavoriteVO> getFavoriteListByMember(MemberVO memberVO) {
        return sqlSession.selectList("myPageMapper.favoriteList", memberVO);
    }

    @Override
    public int addFavorite(FavoriteVO favoriteVO) {
        return sqlSession.insert("myPageMapper.addFavorite", favoriteVO);
    }

    @Override
    public int deleteFav(FavoriteVO favoriteVO) {
        return 0;
    }

}