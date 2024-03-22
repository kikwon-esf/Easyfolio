package com.easyfolio.esf.food.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

@Data
public class FoodVO extends PageVO {
    private String foodCode;
    private String foodTtl;
    private String foodName;
    private String memberName;
    private int foodInqCnt;
    private int foodRcmmCnt;
    private String foodType;
    private String foodUsage;
    private String foodMtrl;
    private String foodKind;
    private String foodIndc;
    private String foodMtrlCn;
    private String foodServe;
    private String foodDif;
    private String foodTime;
    private String foodRegDt;
    private String searchFoodValue;
}
