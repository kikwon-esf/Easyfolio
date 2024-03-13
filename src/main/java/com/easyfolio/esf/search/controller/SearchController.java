package com.easyfolio.esf.search.controller;

import com.easyfolio.esf.search.service.SearchService;
import com.easyfolio.esf.search.vo.SearchVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor

public class SearchController {
    @Resource
    private final SearchService searchService;

    @GetMapping("/searchForm")
    public String searchForm(){

        return "content/search/searchForm";
    }


}