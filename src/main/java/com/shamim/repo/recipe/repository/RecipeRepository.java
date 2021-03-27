package com.shamim.repo.recipe.repository;

import com.shamim.repo.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;



public interface RecipeRepository  extends CrudRepository<Recipe,Long> {
}
