package com.example.diplomska.rest.converters;

import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.rest.dto.RecipeResponseDto;
import org.springframework.stereotype.Service;
import java.util.Base64;
@Service
public class RecipeConverter {
    public RecipeResponseDto from(Recipe recipe) {
        return new RecipeResponseDto(recipe.getId(), recipe.getName(), recipe.getText(), Base64.getEncoder().encode(recipe.getImage()), recipe.getIngredients());
    }
}
