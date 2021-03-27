package com.shamim.repo.recipe.service;


import com.shamim.repo.recipe.commands.RecipeCommand;
import com.shamim.repo.recipe.domain.Recipe;
import java.util.Set;

public interface RecipeService{
    Recipe findById(Long aLong);
    Set<Recipe> getRecipes();
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
