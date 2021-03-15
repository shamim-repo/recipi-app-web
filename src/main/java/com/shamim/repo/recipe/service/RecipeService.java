package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Iterable<Recipe> getRecipesByCategories(Iterable<Long> categories);
}
