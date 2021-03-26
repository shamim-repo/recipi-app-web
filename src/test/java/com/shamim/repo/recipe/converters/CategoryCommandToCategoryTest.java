package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.CategoryCommand;
import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final Long LONG_VALUE =1L;
    public static final String DESCRIPTION ="description";
    CategoryCommandToCategory converter;
    @BeforeEach
    void setUp() {
        converter=new CategoryCommandToCategory();
    }

    @Test
    void nullParameterTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        CategoryCommand command=new CategoryCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        Category converted=converter.convert(command);

        assertNotNull(converted);
        assertEquals(LONG_VALUE,converted.getId());
        assertEquals(DESCRIPTION,converted.getDescription());
    }
}