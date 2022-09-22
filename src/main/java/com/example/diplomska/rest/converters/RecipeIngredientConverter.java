package com.example.diplomska.rest.converters;

import com.example.diplomska.repository.model.RecipeIngredient;
import com.example.diplomska.rest.dto.RecipeIngredientResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientConverter {
    public RecipeIngredientResponseDto from(RecipeIngredient recipeIngredient, String recipeName) {
        return new RecipeIngredientResponseDto(recipeIngredient.getIngredientName(), recipeName, recipeIngredient.getQuantity(),
                recipeIngredient.getProtein(), recipeIngredient.getFat(), recipeIngredient.getCarbohydrate(), recipeIngredient.getEnergy());
    }
}
