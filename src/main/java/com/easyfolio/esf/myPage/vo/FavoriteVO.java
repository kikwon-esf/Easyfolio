package com.easyfolio.esf.myPage.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import com.easyfolio.esf.food.vo.FoodVO;
import lombok.*;

@Data
public class FavoriteVO extends PageVO {
    private String memberId;
    private String foodCode;
    private String favoriteDate;
    private FoodVO foodVO;
    private String searchFavoriteValue;
}