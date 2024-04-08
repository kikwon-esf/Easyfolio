package com.easyfolio.esf.recipe.controller;

import com.easyfolio.esf.recipe.service.RecipeTextService;
import com.easyfolio.esf.recipe.service.RecipeTextService2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class RecipeController {
    private final RecipeTextService recipeTextService;
    private final RecipeTextService2 recipeTextService2;

    public RecipeController(RecipeTextService recipeTextService, RecipeTextService2 recipeTextService2) {
        this.recipeTextService = recipeTextService;
        this.recipeTextService2 = recipeTextService2;
    }

    @GetMapping("/recipeTestForm")
    public String recipeTestForm (Model model) throws IOException {
        model.addAttribute("recipeTest", recipeTextService.getResData());
        System.err.println(recipeTextService.getResData());
        return "content/recipe/recipeTest";
    }

    @GetMapping("/recipeTestForm2")
    public String recipeTestForm2 (Model model) throws IOException {
        model.addAttribute("recipeTest", recipeTextService2.getResData2());
        System.err.println(recipeTextService.getResData());
        return "content/recipe/recipeTest";
    }


}
