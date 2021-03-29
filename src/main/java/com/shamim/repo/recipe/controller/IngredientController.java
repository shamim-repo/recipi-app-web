package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import com.shamim.repo.recipe.service.IngredientService;
import com.shamim.repo.recipe.service.RecipeService;
import com.shamim.repo.recipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",
                        ingredientService.findIngredientByRecipeIdAndId(Long.valueOf(recipeId)
                        ,Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId,Model model){

        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient",ingredientCommand);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("unitOfMeasures",unitOfMeasureService.getUnitOfMeasures());
        return "recipe/ingredient/ingredientform";
    }


    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
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
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
