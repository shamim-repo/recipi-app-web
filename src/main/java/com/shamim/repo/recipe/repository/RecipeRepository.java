package com.shamim.repo.recipe.repository;

import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface RecipeRepository  extends CrudRepository<Recipe, Long> {
    //List<Recipe> getRecipesByCategories(List<Category> categories);
}
