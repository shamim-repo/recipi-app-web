package com.shamim.repo.recipe.repository;

import com.shamim.repo.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryITest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    void setUp() {

    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon",unitOfMeasureOptional.get().getDescription());
    }
    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup",unitOfMeasureOptional.get().getDescription());
    }
}