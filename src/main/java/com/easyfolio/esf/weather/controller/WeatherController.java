package com.easyfolio.esf.weather.controller;

import com.easyfolio.esf.weather.service.WeatherService;
import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/region")
    public String allRegionList(RegionVO regionVO, Model model) {
        regionVO.setNx(60);
        regionVO.setNy(127);
        model.addAttribute("regionList", weatherService.getWeatherByRegion(regionVO));
        return "/content/weather/regionTest";

    }

}

