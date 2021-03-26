package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import com.shamim.repo.recipe.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Long ID=1L;
    public static final String DESCRIPTION="description";
    public static final BigDecimal AMOUNT=new BigDecimal(4);
    IngredientCommandToIngredient ingredientConverter;


    @BeforeEach
    void setUp() {
        UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter=new UnitOfMeasureCommandToUnitOfMeasure();
        ingredientConverter=new IngredientCommandToIngredient(unitOfMeasureConverter);
    }

    @Test
    void nullTest(){
        assertNull(ingredientConverter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        ingredientCommand.setDescription(DESCRIPTION);

        Ingredient ingredient=ingredientConverter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertEquals(ID,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(AMOUNT,ingredient.getAmount());

    }
}