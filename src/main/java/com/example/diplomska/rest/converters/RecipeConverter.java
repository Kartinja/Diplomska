package com.example.diplomska.rest.converters;

import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.rest.dto.RecipeResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RecipeConverter {
    public RecipeResponseDto from(Recipe recipe){
        return new RecipeResponseDto(recipe.getId(), recipe.getName(), recipe.getText(),recipe.getIngredients());
    }
}
