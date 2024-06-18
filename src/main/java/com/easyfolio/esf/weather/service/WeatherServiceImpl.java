package com.easyfolio.esf.weather.service;

import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherResponse;
import com.easyfolio.esf.weather.vo.WeatherVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final SqlSessionTemplate sqlSession;


    @Override
    public List<RegionVO> allRegionList(RegionVO regionVO) {
        return sqlSession.selectList("weatherMapper.allRegionList", regionVO);
    }

}
