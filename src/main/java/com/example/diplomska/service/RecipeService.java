package com.example.diplomska.service;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAll();

    Recipe get(long id);

    List<Ingredient> getIngredients(long id);

    Recipe create(String recipeName, String recipeText,List<Ingredient> ingredients);

    Recipe delete(long id);
}
