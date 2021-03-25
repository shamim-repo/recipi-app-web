package com.shamim.repo.recipe.converters;

import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import com.shamim.repo.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long LONG_VALUE =1L;
    public static final String DESCRIPTION ="description";
    UnitOfMeasureToUnitOfMeasureCommand converter;
    @BeforeEach
    void setUp() {
        converter=new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void nullParameterTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjectTest(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure command=new UnitOfMeasure();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand=converter.convert(command);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(LONG_VALUE,unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION,unitOfMeasureCommand.getDescription());
    }
}