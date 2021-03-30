package com.shamim.repo.recipe.commands;

import com.shamim.repo.recipe.domain.Category;
import com.shamim.repo.recipe.domain.Difficulty;
import com.shamim.repo.recipe.domain.Ingredient;
import com.shamim.repo.recipe.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    private String direction;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Byte[] image;
    private Set<IngredientCommand> ingredients =new HashSet<>();
    private Set<CategoryCommand> categories=new HashSet<>();
}
