package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.NotesCommand;
import com.shamim.repo.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final Long ID=1l;
    public static final String RECIPE_NOTE="RECIPE_NOTE";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter=new NotesToNotesCommand();
    }

    @Test
    void nullParameterTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        Notes notes=new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTE);

        NotesCommand command=converter.convert(notes);

        assertNotNull(notes);
        assertEquals(ID,command.getId());
        assertEquals(RECIPE_NOTE,command.getRecipeNotes());
    }
}