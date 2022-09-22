package com.example.diplomska.service;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.repository.model.RecipeIngredient;
import com.example.diplomska.rest.dto.RecipeRequestDto;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    List<Recipe> getAll();

    Recipe get(long id);

    List<Ingredient> getIngredients(long id);

    Recipe create(RecipeRequestDto recipeRequestDto);

    Set<RecipeIngredient> createRecipeIngredient(RecipeRequestDto recipeRequestDto);

    Recipe updateQuantity(RecipeRequestDto recipeRequestDto, String ingredientName, int quantity);

    List<RecipeIngredient> getAllIngredientQuantity(RecipeRequestDto recipeRequestDto);

    RecipeIngredient getIngredientQuantity(RecipeRequestDto recipeRequestDto, String ingredientName);

    Recipe findByNameAndText(RecipeRequestDto recipeRequestDto);

    Recipe delete(long id);

    List<Recipe> findByIngredient(String name);

    List<Recipe> findByCaloriesRange(Float minCalories, Float maxCalories);
    List<Recipe> findByFatRange(Float minFat, Float maxFat);
    List<Recipe> findByCarbsRange(Float minCarbs, Float maxCarbs);
    List<Recipe> findByProteinRange(Float minProtein, Float maxProtein);
}
