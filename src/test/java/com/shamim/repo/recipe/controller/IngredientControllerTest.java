package com.shamim.repo.recipe.controller;

import com.shamim.repo.recipe.commands.IngredientCommand;
import com.shamim.repo.recipe.commands.RecipeCommand;

import com.shamim.repo.recipe.domain.Ingredient;
import com.shamim.repo.recipe.service.IngredientService;
import com.shamim.repo.recipe.service.RecipeService;

import com.shamim.repo.recipe.service.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    RecipeService service;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;
    MockMvc mockMvc;
    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        controller=new IngredientController(service, ingredientService, unitOfMeasureService);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getIngredientList() throws Exception {
        RecipeCommand command=new RecipeCommand();
        command.setId(1l);
        when(service.findRecipeCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    void testGetIngredientList() throws Exception {
            RecipeCommand recipeCommand=new RecipeCommand();
            recipeCommand.setId(1l);

            when(service.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

            mockMvc.perform(get("/recipe/1/ingredients"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("recipe/ingredient/list"))
                    .andExpect(model().attributeExists("recipe"));

    }

    @Test
    void getIngredient() throws Exception {
        IngredientCommand command=new IngredientCommand();
        command.setRecipeId(1l);
        command.setId(2l);

        when(ingredientService.findIngredientByRecipeIdAndId(anyLong(),anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }

    @Test
    void updateIngredient() throws Exception {
        IngredientCommand command=new IngredientCommand();
        command.setId(1l);
        command.setRecipeId(2l);

        when(ingredientService.findIngredientByRecipeIdAndId(anyLong(),anyLong())).thenReturn(command);
        when(unitOfMeasureService.getUnitOfMeasures()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("unitOfMeasures"));

    }

    @Test
    void saveOrUpdate() throws Exception {
        IngredientCommand command=new IngredientCommand();
        command.setId(1l);
        command.setRecipeId(2l);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id",""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

    }

    @Test
    void newIngredient() throws Exception {
        IngredientCommand command=new IngredientCommand();
        command.setId(1l);
        command.setRecipeId(2l);
        when(unitOfMeasureService.getUnitOfMeasures()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("unitOfMeasures"));

    }

    @Test
    void deleteIngredient() throws Exception {
        mockMvc.perform(get("/recipe/2/ingredient/1/delete"))
                .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/2/ingredients"));
        verify(ingredientService,times(1)).deleteIngredientByRecipeIdAndId(anyLong(),anyLong());
    }
}