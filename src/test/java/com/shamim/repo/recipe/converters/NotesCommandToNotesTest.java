package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.NotesCommand;
import com.shamim.repo.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final Long ID=1l;
    public static final String RECIPE_NOTE="RECIPE_NOTE";
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter=new NotesCommandToNotes();
    }

    @Test
    void nullParameterTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        NotesCommand command=new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(RECIPE_NOTE);

        Notes notes=converter.convert(command);

        assertNotNull(notes);
        assertEquals(ID,notes.getId());
        assertEquals(RECIPE_NOTE,notes.getRecipeNotes());
    }
}