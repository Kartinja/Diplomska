package com.example.diplomska.service;

import com.example.diplomska.repository.model.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAll();

    Ingredient create(String name,String description);

    Ingredient get(long id);

    List<Ingredient> findAllByRecipeId(Long id);
}
