package com.easyfolio.esf.recipe.vo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RecipeTextVO {
    public String recipeCode;
    public String textCode;
    public String recipeContent;
}
