package com.easyfolio.esf.weather.service;

import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherResponse;
import com.easyfolio.esf.weather.vo.WeatherVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final SqlSessionTemplate sqlSession;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<RegionVO> allRegionList(RegionVO regionVO) {
        return sqlSession.selectList("weatherMapper.allRegionList", regionVO);
    }

    @Override
    public WeatherVO getWeatherByRegion(RegionVO regionVO) {
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                + "?serviceKey={serviceKey}"
                + "&pageNo=1"
                + "&numOfRows=10"
                + "&dataType=XML"
                + "&base_date={baseDate}"
                + "&base_time={baseTime}"
                + "&nx={nx}"
                + "&ny={ny}";

        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(
                apiUrl,
                WeatherResponse.class,
                "${weather.api.key}",
                "20240618",
                "0900",
                regionVO.getNx(),
                regionVO.getNy()
        );

        WeatherVO weatherVO = new WeatherVO();
        // 응답 데이터에서 필요한 정보(기온, 습도, 강수량 등)를 추출하여 WeatherVO에 설정
        return weatherVO;
    }
}
