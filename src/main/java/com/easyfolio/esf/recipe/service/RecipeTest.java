package com.easyfolio.esf.recipe.service;

import com.easyfolio.esf.recipe.vo.RecipeTextVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class RecipeTest {

    Document document = Jsoup.connect("https://www.10000recipe.com/recipe/6993517").get();

    public RecipeTest() throws IOException {
        Element title = document.getElementById("contents_area_full");


//        for (Element content : title) {
//            RecipeTextVO recipeText = RecipeTextVO.builder()
//                    .recipeContent(content.select("div#obx_recipe_step_start div.view_step_cont").text())
//                    .recipeTitle(content.select("div.view2_summary.st3 h3").text())
//                    .build();
//            resData.add(recipeText);
//        }
    }
}
