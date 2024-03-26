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
    public List<FavoriteVO> getFavoriteListByMember(FavoriteVO favoriteVO) {
        return sqlSession.selectList("myPageMapper.favoriteList", favoriteVO);
    }

    @Override
    public int addFavorite(FavoriteVO favoriteVO) {
        return sqlSession.insert("myPageMapper.addFavorite", favoriteVO);
    }

    @Override
    public int deleteFav(FavoriteVO favoriteVO) {
        return sqlSession.delete("myPageMapper.deleteFav", favoriteVO);
    }

    @Override
    public int addFav(FavoriteVO favoriteVO) {
        return sqlSession.insert("myPageMapper.addFavorite", favoriteVO);
    }

    @Override
    public int favoriteCnt(FavoriteVO favoriteVO) {
        return sqlSession.selectOne("myPageMapper.favoriteCnt", favoriteVO);
    }

}
