package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.CategoryCommand;
import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.commands.NotesCommand;
import com.shamim.repo.recipe.commands.RecipeCommand;
import com.shamim.repo.recipe.domain.Difficulty;
import com.shamim.repo.recipe.domain.Notes;
import com.shamim.repo.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Long LONG_VALUE=1L;
    public static final String DESCRIPTION="DESCRIPTION";
    public static final Integer PREP_TIME=1;
    public static final Integer COOK_TIME=1;
    public static final Integer SERVING=1;
    public static final String SOURCE="SOURCE";
    public static final String URL="URL";
    public static final String DIRECTION="DIRECTION";
    public static final Difficulty DIFFICULTY=Difficulty.EASY;
    public static final NotesCommand notes=new NotesCommand();
    public Set<IngredientCommand> ingredients =new HashSet<>();
    public Set<CategoryCommand> categories=new HashSet<>();

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        notes.setId(5L);
        ingredients.add(new IngredientCommand());
        categories.add(new CategoryCommand());

        converter=new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());

    }

    @Test
    void nullTest() {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand command=new RecipeCommand();
        command.setDescription(DESCRIPTION);
        command.setDirection(DESCRIPTION);
        command.setCookTime(COOK_TIME);
        command.setDifficulty(DIFFICULTY);
        command.setNotes(notes);
        command.setId(LONG_VALUE);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServing(SERVING);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setCategories(categories);
        command.setIngredients(ingredients);

        Recipe recipe =converter.convert(command);
        assertNotNull(recipe);
        assertEquals(LONG_VALUE,recipe.getId());
        assertEquals(DIFFICULTY,recipe.getDifficulty());
        assertEquals(categories.size(),recipe.getCategories().size());
        assertEquals(ingredients.size(),recipe.getIngredients().size());
        assertEquals(notes.getId(),recipe.getNotes().getId());

    }
}