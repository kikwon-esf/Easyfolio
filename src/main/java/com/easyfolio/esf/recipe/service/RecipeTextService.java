package com.easyfolio.esf.recipe.service;

import com.easyfolio.esf.recipe.vo.RecipeTextVO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeTextService {
    private static String Res_URL = "https://www.10000recipe.com/recipe/6993517";

//    @PostConstruct
//    public List<RecipeTextVO> getResData() throws IOException {
//        List<RecipeTextVO> resData = new ArrayList<>();
//        Document document = Js.connect(Res_URL).get();
//
//        Elements contents = document.select("div#obx_recipe_step_start div.media");
//
//
//        for (Element content : contents) {
//            CRVO res = CRVO.builder()
//                    .recipeText(content.select("div.media-body").text())
//                    .build();
//            resData.add(res);
//        }
//
//        return resData;
//    }

}
