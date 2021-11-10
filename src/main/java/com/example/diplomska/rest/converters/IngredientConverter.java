package com.example.diplomska.rest.converters;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.rest.dto.IngredientResponseDto;
import org.springframework.stereotype.Service;

@Service
public class IngredientConverter {
    public IngredientResponseDto from(Ingredient ingredient){
        return new IngredientResponseDto(ingredient.getName(),ingredient.getUrl()
        );
    }
}
