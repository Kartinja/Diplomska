package com.example.diplomska.service.impl;

import com.example.diplomska.repository.IngredientJpaRepository;
import com.example.diplomska.repository.RecipeJpaRepository;
import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private RecipeJpaRepository recipeJpaRepository;
    private IngredientJpaRepository ingredientJpaRepository;

    @Override
    public List<Recipe> getAll() {
        return recipeJpaRepository.findAll();
    }

    @Override
    public Recipe get(long id) {
        return recipeJpaRepository.getById(id);
    }

    @Override
    public List<Ingredient> getIngredients(long id) {
        return null;
    }

    @Override
    public Recipe create(String recipeName, String recipeText,List<Ingredient> ingredient) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setText(recipeText);
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient value : ingredient) {
            if (ingredientJpaRepository.existsByName(value.getName())) {
                ingredients.add(ingredientJpaRepository.getByName(value.getName()));
            } else {
                ingredientJpaRepository.save(value);
                ingredients.add(value);
            }
        }
        recipe.setIngredients(ingredients);
        return recipeJpaRepository.save(recipe);
    }

    @Override
    public Recipe delete(long id) {
        Recipe recipe = recipeJpaRepository.getById(id);
        recipeJpaRepository.delete(recipe);
        return recipe;
    }
}
