package com.easyfolio.esf.weather.vo;

import com.easyfolio.esf.csc.vo.PageVO;
import lombok.Data;

@Data
public class DdabongVO extends PageVO {
    private String ddabongCode;
    private String ddabongNow;
    private String ddabongFood;

}