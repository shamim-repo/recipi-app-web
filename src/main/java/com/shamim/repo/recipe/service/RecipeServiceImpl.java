package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Iterable<Recipe> getRecipesByCategories(Iterable<Long> categories) {

        return recipeRepository.findAllById(categories);
    }
}
