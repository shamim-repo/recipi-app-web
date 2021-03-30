package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import com.shamim.repo.recipe.service.IngredientService;
import com.shamim.repo.recipe.service.RecipeService;
import com.shamim.repo.recipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",
                        ingredientService.findIngredientByRecipeIdAndId(Long.valueOf(recipeId)
                        ,Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId,Model model){

        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient",ingredientCommand);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("unitOfMeasures",unitOfMeasureService.getUnitOfMeasures());
        return "recipe/ingredient/ingredientform";
    }
    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){

        ingredientService.deleteIngredientByRecipeIdAndId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId));

        return "redirect:/recipe/"+recipeId+"/ingredients";
    }


    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",
                ingredientService.findIngredientByRecipeIdAndId(Long.valueOf(recipeId)
                        ,Long.valueOf(ingredientId)));
        model.addAttribute("unitOfMeasures",unitOfMeasureService.getUnitOfMeasures());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients";
    }
}
