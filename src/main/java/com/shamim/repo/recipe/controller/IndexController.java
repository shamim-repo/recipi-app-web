package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping({"","/","/index","/index.html"})
    public String getRecipe(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }

}
