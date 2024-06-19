package com.easyfolio.esf.weather.vo;

import lombok.Data;

@Data
public class RegionVO {
    private String areaCode;
    private String regionId;
    private String regionParent;
    private String regionChild;
    private int nx;
    private int ny;

}
