package com.easyfolio.esf.myPage.vo;

import lombok.*;
import oracle.sql.DATE;

@Data
public class FavoriteOutVO {
    private String foodCode;
    private String memberId;
    private String foodTtl;
    private String foodName;
    private String favoriteDate;
}
