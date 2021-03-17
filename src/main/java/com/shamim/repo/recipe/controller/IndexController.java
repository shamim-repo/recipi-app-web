package com.shamim.repo.recipe.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shamim.repo.recipe.controller.requestBody.RecipeByCategoryRequestBody;
import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.service.CategoryService;
import com.shamim.repo.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Ids;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public IndexController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }


    @RequestMapping({"","/","/index","index.html"})
    public String getRecipe(Model model){
        //log.debug("Getting recipe");
        //Set<Recipe> recipes=new HashSet<>();
        //recipeService.getRecipes().iterator().forEachRemaining(recipes::add);

        model.addAttribute("recipes",recipeService.getRecipes());

        return "index";
    }

  /*   @GetMapping("/recipes")
    public Set<Recipe> getRecipe(){
        log.debug("Getting recipe");
        Set<Recipe> recipes=new HashSet<>();
        recipeService.getRecipes().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

   @GetMapping("/recipesById")
    public Set<Recipe> getRecipe(@RequestBody RecipeByCategoryRequestBody recipeByCategoryRequestBody){
        log.debug("Getting recipe by ids");
        Set<Recipe> recipes=new HashSet<>();
        Iterable<Long> iterable=recipeByCategoryRequestBody.getIds();
        System.out.println("iterable ="+iterable);
        System.out.println("ArrayList ="+recipeByCategoryRequestBody.getIds());
        recipeService.getRecipesByCategories(iterable).iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @GetMapping("/categories")
    public Set<Category> getCategories(){
        return categoryService.getCategories();
    }
     */
}
