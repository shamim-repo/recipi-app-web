package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.commands.RecipeCommand;
import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        Recipe recipe=recipeService.findRecipeById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById((Long.valueOf(id))));
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id :"+id);
        recipeService.deleteRecipe(Long.valueOf(id));
        return "redirect:/";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommend=recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/"+savedCommend.getId()+"/show";
    }

}
