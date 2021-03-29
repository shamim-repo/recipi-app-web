package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.converters.IngredientCommandToIngredient;
import com.shamim.repo.recipe.converters.IngredientToIngredientCommand;
import com.shamim.repo.recipe.domain.Ingredient;
import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.repository.IngredientRepository;
import com.shamim.repo.recipe.repository.RecipeRepository;
import com.shamim.repo.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository repository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand toIngredientCommand;
    private final IngredientCommandToIngredient toIngredient;

    public IngredientServiceImpl(IngredientRepository repository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, IngredientToIngredientCommand toIngredientCommand, IngredientCommandToIngredient toIngredient) {
        this.repository = repository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.toIngredientCommand = toIngredientCommand;
        this.toIngredient = toIngredient;
    }

    @Override
    public Ingredient findIngredientById(Long aLong) {
        Optional<Ingredient> optional = repository.findById(aLong);
        if (!optional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        return optional.get();
    }

    @Override
    public IngredientCommand findIngredientByRecipeIdAndId(Long aLong, Long bLong) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(aLong);

        if (!recipeOptional.isPresent()){
            log.error("recipe id not found. Id: " + aLong);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(bLong))
                .map( ingredient -> toIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("Ingredient id not found: " + bLong);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand findIngredientCommandById(Long aLong) {
        return toIngredientCommand.convert(findIngredientById(aLong));
    }

    @Override
    public IngredientCommand findIngredientCommandByRecipeIdAndId(Long aLong, Long bLong) {
        return null;
    }

    @Override
    public Set<Ingredient> getIngredients() {
        return null;
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if(!recipeOptional.isPresent()){
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            } else {
                recipe.addIngredient(toIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);


            return toIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getDescription().equalsIgnoreCase(command.getDescription()))
                    .filter(ingredient -> ingredient.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
                    .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                    .findFirst()
                    .get());
        }

    }

    @Override
    public void deleteIngredient(Long aLong) {

    }

}
