package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.converters.IngredientCommandToIngredient;
import com.shamim.repo.recipe.converters.IngredientToIngredientCommand;
import com.shamim.repo.recipe.domain.Ingredient;
import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.exception.NotFoundException;
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

    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand toIngredientCommand;
    private final IngredientCommandToIngredient toIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, IngredientToIngredientCommand toIngredientCommand, IngredientCommandToIngredient toIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.toIngredientCommand = toIngredientCommand;
        this.toIngredient = toIngredient;
    }

    @Override
    public Ingredient findIngredientById(Long aLong) {
        Optional<Ingredient> optional = ingredientRepository.findById(aLong);

        if (!optional.isPresent()) {
            throw new NotFoundException("Ingredient Not Found By Id +"+aLong);
        }
        return optional.get();
    }

    @Override
    public IngredientCommand findIngredientByRecipeIdAndId(Long aLong, Long bLong) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(aLong);

        if (!recipeOptional.isPresent()){
            log.error("recipe id not found. Id: " + aLong);
            throw new NotFoundException("Recipe Not Found by id: "+aLong);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(bLong))
                .map( ingredient -> toIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            throw new RuntimeException("Ingredient Not Found By Id +"+bLong);
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
    public void deleteIngredientByRecipeIdAndId(Long aLong, Long bLong) {
            Optional<Recipe> recipeOptional=recipeRepository.findById(aLong);

            if(!recipeOptional.isPresent()){
                throw new RuntimeException("No recipe present by id :"+aLong);
            }else {
                Recipe recipe=recipeOptional.get();
               Optional<Ingredient> ingredientOptional=recipe
                       .getIngredients().stream()
                       .filter(ingredient1 -> ingredient1.getId().equals(bLong))
                       .findFirst();

               if (!ingredientOptional.isPresent()){
                   throw new RuntimeException("No ingredient present by id :"+aLong);
               }else {
                   Ingredient ingredientToDelete=ingredientOptional.get();
                   ingredientToDelete.setRecipe(null);
                   recipe.getIngredients().remove(ingredientOptional.get());
                   recipeRepository.save(recipe);
               }
            }
    }

}
