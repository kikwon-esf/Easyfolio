package com.easyfolio.esf.recipe.service;

import com.easyfolio.esf.recipe.vo.RecipeTextVO;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeTextService {
    private static String Res_URL = "https://www.10000recipe.com/recipe/6993517";

    @PostConstruct
    public List<RecipeTextVO> getResData() throws IOException {
        List<RecipeTextVO> resData = new ArrayList<>();
        Document document = Jsoup.connect(Res_URL).get();

        Elements contents = document.select("div#obx_recipe_step_start div.media");


        for (Element content : contents) {
            RecipeTextVO recipeText = RecipeTextVO.builder()
                    .recipeContent(content.select("div.media-body").text())
                    .build();
            resData.add(recipeText);
        }

        return resData;
    }

}
