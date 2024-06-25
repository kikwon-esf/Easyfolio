package com.easyfolio.esf.food.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

import java.util.List;

@Data
public class FoodVO extends PageVO {
    private String foodCode;
    private String foodTtl;
    private String foodName;
    private String memberName;
    private int foodInqCnt;
    private int foodRcmmCnt;
    private String foodTypeCode;
    private String foodUsageCode;
    private String foodMtrlCode;
    private String foodKindCode;
    private String foodIndc;
    private String foodMtrlCn;
    private String foodServe;
    private String foodDif;
    private String foodTime;
    private String foodRegDt;
    private String searchFoodValue;
    private FoodKindVO foodKindVO;
    private FoodUsageVO foodUsageVO;
    private FoodMtrlVO foodMtrlVO;
    private FoodTypeVO foodTypeVO;
    private String memberId;
    private FoodImgVO foodImgVO;
    private int foodCommentCnt;
    private List<String> foodNames;
}
