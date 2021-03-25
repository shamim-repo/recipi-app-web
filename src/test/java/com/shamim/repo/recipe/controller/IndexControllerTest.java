package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.domain.Recipe;
import com.shamim.repo.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeService);
    }


    @Test
    void mockMvc() {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
        //mockMvc.perform(get()).andExpect(status().isOk()).andExpect(view().name("index"));

    }

    @Test
    void getRecipe() {

        //given
        Recipe recipe=new Recipe();
        HashSet<Recipe> recipes=new HashSet<>();
        recipes.add(recipe);
        recipe.setDescription("test");
        recipes.add(recipe);
        when(recipeService.findAll()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> setArgumentCaptor=ArgumentCaptor.forClass(Set.class);
        //when
        String viewName= indexController.getRecipe(model);

       //then
        assertEquals("index",viewName);
        verify(recipeService, times(1)).findAll();
        verify(model,times(1)).addAttribute(eq("recipes"),setArgumentCaptor.capture());
        Set<Recipe> setInController=setArgumentCaptor.getValue();
        assertEquals(2,setInController.size());
    }
}