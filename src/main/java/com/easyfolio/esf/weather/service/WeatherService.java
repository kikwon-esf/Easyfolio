package com.easyfolio.esf.weather.service;

import com.easyfolio.esf.weather.vo.DdabongVO;
import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherVO;

import java.util.List;

public interface WeatherService {
    public List<RegionVO> regionParent();

    public List<RegionVO> regionChild();

    List<DdabongVO> ddabongFoodList(String ddabongCode);

}
