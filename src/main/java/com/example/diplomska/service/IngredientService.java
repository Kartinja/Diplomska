package com.example.diplomska.service;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.rest.dto.UsdaResponseDto;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAll();

    Ingredient post(UsdaResponseDto usdaResponseDto, String name);

    Ingredient get(String name);

    //List<Ingredient> findAllByRecipeId(Long id);
}
