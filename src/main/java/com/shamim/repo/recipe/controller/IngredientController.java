package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService service;

    public IngredientController(RecipeService service) {
        this.service = service;
    }

    @RequestMapping("recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable String id, Model model){
        model.addAttribute("recipe",service.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }
}
