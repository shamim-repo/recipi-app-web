package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.domain.Ingredient;

import java.util.Set;

public interface IngredientService {
    Ingredient findIngredientById(Long aLong);
    IngredientCommand findIngredientByRecipeIdAndId(Long aLong,Long bLong);
    IngredientCommand findIngredientCommandById(Long aLong);
    IngredientCommand findIngredientCommandByRecipeIdAndId(Long aLong,Long bLong);
    Set<Ingredient> getIngredients();
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteIngredientByRecipeIdAndId(Long aLong,Long bLong);
}
