package com.easyfolio.esf.myPage.service;

import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;
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
    public List<String> getFavoriteListString(MemberVO memberVO) {
        return sqlSession.selectList("myPageMapper.favoriteListString", memberVO);
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
    @Override
    public int increaseFavCnt(FavoriteVO favoriteVO) {
        return sqlSession.update("myPageMapper.increaseFavCnt",favoriteVO);
    }

    @Override
    public int decreaseFavCnt(FavoriteVO favoriteVO) {
        return sqlSession.insert("myPageMapper.decreaseFavCnt", favoriteVO);
    }

    @Override
    public int submitComment(CommentVO commentVO) {
        return sqlSession.insert("commentMapper.submitComment",commentVO);
    }

    @Override
    public int deleteComment(CommentVO commentVO) {
        return sqlSession.insert("commentMapper.deleteFoodComment",commentVO);
    }

    @Override
    public int updateComment(CommentVO commentVO) {
        return sqlSession.insert("commentMapper.updateFoodComment",commentVO);
    }

    @Override
    public List<CommentVO> getCommentVOList(CommentVO commentVO) {
        return sqlSession.selectList("commentMapper.commentList",commentVO);
    }

}
