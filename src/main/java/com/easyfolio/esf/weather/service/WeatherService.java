package com.easyfolio.esf.weather.service;

import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherVO;

import java.util.List;

public interface WeatherService {
    public List<RegionVO> allRegionList(RegionVO regionVO);

    public WeatherVO getWeatherByRegion(RegionVO regionVO);

}
