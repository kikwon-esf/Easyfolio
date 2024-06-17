package com.easyfolio.esf.myPage.service;

import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.CommentVO;
import com.easyfolio.esf.myPage.vo.FavoriteVO;

import java.util.List;
import java.util.Map;

public interface MyPageService {
    public List<FavoriteVO> getFavoriteListByMember(FavoriteVO favoriteVO);

    public List<String> getFavoriteListString(MemberVO memberVO);

    public int addFavorite(FavoriteVO favoriteVO);

    //즐겨찾기 삭제
    public int deleteFav(FavoriteVO favoriteVO);

    public int addFav(FavoriteVO favoriteVO);

    public int favoriteCnt(FavoriteVO favoriteVO);

    public int increaseFavCnt(FavoriteVO favoriteVO);

    public int decreaseFavCnt(FavoriteVO favoriteVO);

    public String nextComtCode();

    public void submitComment(CommentVO commentVO);


    public int deleteComment(CommentVO commentVO);

    public int updateComment(CommentVO commentVO);

    public Map<String,CommentVO> getCommentVOList(CommentVO commentVO);

    public List<CommentVO> getReComment(CommentVO commentVO);

    public List<FoodVO> getFoodByMember(FoodVO foodVO);

    public List<CommentVO> getCommentByMember(CommentVO commentVO);

}
