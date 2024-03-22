package com.easyfolio.esf.myPage.vo;

import com.easyfolio.esf.food.vo.FoodVO;
import lombok.*;

@Data
public class FavoriteVO {
    private String memberId;
    private String foodCode;
    private String favoriteDate;
    private FoodVO foodVO;
}
