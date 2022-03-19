package com.example.diplomska.service.impl;

import com.example.diplomska.repository.IngredientJpaRepository;
import com.example.diplomska.repository.RecipeJpaRepository;
import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.rest.dto.*;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private RecipeJpaRepository recipeJpaRepository;
    private IngredientJpaRepository ingredientJpaRepository;
    private RestTemplate restTemplate;

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
    public Recipe create(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeRequestDto.getName());
        recipe.setText(recipeRequestDto.getText());
        Set<Ingredient> ingredientSetList = new LinkedHashSet<>();

        String urlFinki = "http://foodviz.env4health.finki.ukim.mk/predict?text="
                + recipeRequestDto.getText() + "&model=bioBert-standard-model-foodon-e100-0.0005.bin";

        TokensRequestDto tokensRequestDto = restTemplate.getForObject(urlFinki, TokensRequestDto.class);

        //Gi vadam koi se ingredients od od tekstot
        for (IngredientToken ingredientToken : Objects.requireNonNull(tokensRequestDto).getTokens()) {
            if (ingredientToken.getOtherTags() != null) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientToken.getWord());
                ingredientSetList.add(ingredient);
            }
        }
        if(ingredientSetList.isEmpty()){
            throw new Error("The recipe you add must contain ingredients.");
        }

        List<Ingredient> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : ingredientSetList) {
            //ako postoi vo baza ingredient dodadi go vo listata
            if (ingredientJpaRepository.existsByName(ingredient.getName())) {
                ingredient = ingredientJpaRepository.getByName(ingredient.getName());

            } else {
                //ako ne postoi vo baza
                String urlUsda = "https://api.nal.usda.gov/fdc/v1/foods/search?query=" + ingredient.getName() + "&pageSize=2&api_key=pfmU6uLGv9pUvJz9oocLidkY8cKDxLsV27c9cn7t";
                UsdaResponseDto usdaResponseDto = restTemplate.getForObject(urlUsda, UsdaResponseDto.class);

                List<FoodNutrientDto> foodNutrients = usdaResponseDto.getFoods().get(0).getFoodNutrients();
                for (FoodNutrientDto foodNutrient : foodNutrients) {
                    if (foodNutrient.getNutrientName().toLowerCase().contains("protein")) {
                        ingredient.setProtein(foodNutrient.getValue());
                    }
                    if (foodNutrient.getNutrientName().toLowerCase(Locale.ROOT).contains("carbohydrate")) {
                        ingredient.setCarbohydrate(foodNutrient.getValue());
                    }
                    if (foodNutrient.getNutrientName().toLowerCase(Locale.ROOT).contains("fat")) {
                        ingredient.setFat(foodNutrient.getValue());
                    }
                    if (foodNutrient.getNutrientName().toLowerCase(Locale.ROOT).contains("energy")) {
                        ingredient.setEnergy(foodNutrient.getValue());
                    }
                }
                ingredientJpaRepository.save(ingredient);
            }
            ingredientList.add(ingredient);
        }
        recipe.setIngredients(ingredientList);
        return recipeJpaRepository.save(recipe);
    }

    @Override
    public Recipe delete(long id) {
        Recipe recipe = recipeJpaRepository.getById(id);
        recipeJpaRepository.delete(recipe);
        return recipe;
    }

    @Override
    public List<Recipe> findByIngredient(String name) {
        return recipeJpaRepository.findAllByIngredient(name);
    }
}
