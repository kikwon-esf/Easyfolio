package com.easyfolio.esf.myPage.service;

import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.vo.FavoriteVO;

import java.util.List;

public interface MyPageService {
    public List<FavoriteVO> getFavoriteListByMember(FavoriteVO favoriteVO);

    public List<String> getFavoriteListString(MemberVO memberVO);

    public int addFavorite(FavoriteVO favoriteVO);

    //즐겨찾기 삭제
    public int deleteFav(FavoriteVO favoriteVO);

    public int addFav(FavoriteVO favoriteVO);

    public int favoriteCnt(FavoriteVO favoriteVO);



}
