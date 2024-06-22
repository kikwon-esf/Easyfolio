package com.easyfolio.esf.weather.controller;

import com.easyfolio.esf.weather.service.WeatherService;
import com.easyfolio.esf.weather.vo.DdabongVO;
import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/region")
    public String allRegionList(RegionVO regionVO, Model model) {

        model.addAttribute("regionParents", weatherService.regionParent());
        model.addAttribute("regionChilds", weatherService.regionChild());
        return "/content/food/weatherFood";
    }

    @GetMapping("/food")
    public String foodWeather(DdabongVO ddabongVO, Model model) {
        model.addAttribute("weather", weatherService.ddabongFoodList(ddabongVO.getDdabongCode()));
        return "/content/food/weatherFood_direct";
    }
}