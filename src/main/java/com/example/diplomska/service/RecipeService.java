package com.example.diplomska.service;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.rest.dto.TokensRequestDto;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAll();

    Recipe get(long id);

    List<Ingredient> getIngredients(long id);

    Recipe create(String recipeName, String recipeText, TokensRequestDto tokensRequestDto);

    Recipe delete(long id);
}
