package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.domain.Ingredient;
import com.shamim.repo.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Long ID=1L;
    public static final String DESCRIPTION="description";
    public static final BigDecimal AMOUNT=new BigDecimal(4);
    IngredientToIngredientCommand ingredientConverter;


    @BeforeEach
    void setUp() {
        UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter=new UnitOfMeasureToUnitOfMeasureCommand();
        ingredientConverter=new IngredientToIngredientCommand(unitOfMeasureConverter);
    }

    @Test
    void nullTest(){
        assertNull(ingredientConverter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient ingredient=new Ingredient();
        ingredient.setId(ID);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(new UnitOfMeasure());
        ingredient.setDescription(DESCRIPTION);

        IngredientCommand ingredientCommand=ingredientConverter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertEquals(ID,ingredientCommand.getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(AMOUNT,ingredientCommand.getAmount());

    }
}