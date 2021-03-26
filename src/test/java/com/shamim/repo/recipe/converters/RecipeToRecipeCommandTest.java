package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.RecipeCommand;
import com.shamim.repo.recipe.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {
    public static final Long LONG_VALUE=1L;
    public static final String DESCRIPTION="DESCRIPTION";
    public static final Integer PREP_TIME=1;
    public static final Integer COOK_TIME=1;
    public static final Integer SERVING=1;
    public static final String SOURCE="SOURCE";
    public static final String URL="URL";
    public static final String DIRECTION="DIRECTION";
    public static final Difficulty DIFFICULTY=Difficulty.EASY;
    public static final Notes notes=new Notes();
    public Set<Ingredient> ingredients =new HashSet<>();
    public Set<Category> categories=new HashSet<>();

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        notes.setId(5L);
        ingredients.add(new Ingredient());
        categories.add(new Category());

        converter=new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());

    }

    @Test
    void nullTest() {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setDescription(DESCRIPTION);
        recipe.setDirection(DESCRIPTION);
        recipe.setCookTime(COOK_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(notes);
        recipe.setId(LONG_VALUE);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServing(SERVING);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(categories.size(), command.getCategories().size());
        assertEquals(ingredients.size(), command.getIngredients().size());
        assertEquals(notes.getId(), command.getNotes().getId());
    }
}