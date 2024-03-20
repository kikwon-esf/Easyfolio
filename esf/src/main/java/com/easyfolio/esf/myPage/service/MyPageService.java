package com.easyfolio.esf.myPage.service;

import com.easyfolio.esf.myPage.vo.FavoriteInputVO;
import com.easyfolio.esf.myPage.vo.FavoriteOutVO;

import java.util.List;

public interface MyPageService {
    public List<FavoriteOutVO> getFavoriteListByMember();

    public int addFavorite(FavoriteInputVO favoriteInputVO);
}
