package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.commands.RecipeCommand;
import com.shamim.repo.recipe.converters.RecipeCommandToRecipe;
import com.shamim.repo.recipe.converters.RecipeToRecipeCommand;
import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.exception.NotFoundException;
import com.shamim.repo.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Recipe findRecipeById(Long aLong) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(aLong);
        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found By Id :"+aLong);
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long aLong){
        return recipeToRecipeCommand.convert(findRecipeById(aLong));
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("savedRecipe ID :"+savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteRecipe(Long aLong) {
        recipeRepository.deleteById(aLong);
    }
}
