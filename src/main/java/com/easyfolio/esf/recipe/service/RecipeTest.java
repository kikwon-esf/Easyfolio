package com.easyfolio.esf.recipe.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RecipeTest {

    Document document = Jsoup.connect("https://www.10000recipe.com/recipe/6993517").get();

    public RecipeTest() throws IOException {
    }
}
