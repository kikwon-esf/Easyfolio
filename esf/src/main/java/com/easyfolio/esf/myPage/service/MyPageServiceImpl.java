package com.easyfolio.esf.myPage.service;

import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.FavoriteInputVO;
import com.easyfolio.esf.myPage.vo.FavoriteOutVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<FavoriteOutVO> getFavoriteListByMember(MemberVO memberVO) {
        System.err.println("serviceImpl");
        return sqlSession.selectList("myPageMapper.favoriteList", memberVO);
    }

    @Override
    public int addFavorite(FavoriteInputVO favoriteInputVO) {
        return sqlSession.insert("myPageMapper.addFavorite", favoriteInputVO);
    }

}
