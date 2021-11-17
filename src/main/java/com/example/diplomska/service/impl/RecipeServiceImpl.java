package com.example.diplomska.service.impl;

import com.example.diplomska.repository.IngredientJpaRepository;
import com.example.diplomska.repository.RecipeJpaRepository;
import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.rest.dto.IngredientToken;
import com.example.diplomska.rest.dto.TokensRequestDto;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Recipe create(String recipeName, String recipeText, TokensRequestDto tokensRequestDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setText(recipeText);
        List<Ingredient> ingredientList = new ArrayList<>();

        for (IngredientToken ingredientToken:tokensRequestDto.getTokens()) {
            if(ingredientToken.getOtherTags()!=null){
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientToken.getWord().toString());
                String url = "https://lookup.dbpedia.org/api/search?maxResults=10&format=JSON&query="+ingredient.getName();
                ingredient.setUrl(url);
                ingredientList.add(ingredient);
            }
        }

        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient value : ingredientList) {
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
