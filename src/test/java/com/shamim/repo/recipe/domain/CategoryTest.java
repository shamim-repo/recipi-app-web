package com.shamim.repo.recipe.domain;

import org.junit.Before;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category=new Category();
    }

    @org.junit.Test
    public void getId() {
        Long id=4l;
        category.setId(id);
        assertEquals(id,category.getId());
    }

    @org.junit.Test
    public void getDescription() {
    }

    @org.junit.Test
    public void getRecipes() {
    }
}