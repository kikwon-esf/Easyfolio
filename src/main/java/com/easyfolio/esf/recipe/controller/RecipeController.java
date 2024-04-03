package com.easyfolio.esf.recipe.controller;

import com.easyfolio.esf.recipe.service.RecipeTextService;
import org.springframework.stereotype.Controller;

@Controller
public class RecipeController {
    private final RecipeTextService recipeTextService;

    public RecipeController(RecipeTextService recipeTextService) {
        this.recipeTextService = recipeTextService;
    }
}
