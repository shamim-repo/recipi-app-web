package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        Optional<Recipe> recipe=recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe.get());
        return "recipe/show";
    }
}
