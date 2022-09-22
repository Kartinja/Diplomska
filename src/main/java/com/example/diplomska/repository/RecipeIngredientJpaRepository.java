package com.example.diplomska.repository;

import com.example.diplomska.repository.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientJpaRepository extends JpaRepository<RecipeIngredient, Long> {
    RecipeIngredient findRecipeIngredientByRecipeIdAndIngredientName(Long recipeId,String ingredientName);
    List<RecipeIngredient> findAllByRecipeId(Long id);
}
