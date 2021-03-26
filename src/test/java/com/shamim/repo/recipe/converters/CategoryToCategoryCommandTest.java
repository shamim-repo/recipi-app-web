package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.CategoryCommand;
import com.shamim.repo.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final Long LONG_VALUE =1L;
    public static final String DESCRIPTION ="description";
    CategoryToCategoryCommand converter;
    @BeforeEach
    void setUp() {
        converter=new CategoryToCategoryCommand();
    }

    @Test
    void nullParameterTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        Category category=new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand converted=converter.convert(category);

        assertNotNull(converted);
        assertEquals(LONG_VALUE,converted.getId());
        assertEquals(DESCRIPTION,converted.getDescription());
    }
}