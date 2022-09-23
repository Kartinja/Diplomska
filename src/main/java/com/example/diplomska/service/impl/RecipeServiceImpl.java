package com.example.diplomska.service.impl;

import com.example.diplomska.repository.IngredientJpaRepository;
import com.example.diplomska.repository.RecipeIngredientJpaRepository;
import com.example.diplomska.repository.RecipeJpaRepository;
import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.repository.model.RecipeIngredient;
import com.example.diplomska.rest.dto.*;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private RecipeJpaRepository recipeJpaRepository;
    private IngredientJpaRepository ingredientJpaRepository;
    private RecipeIngredientJpaRepository recipeIngredientJpaRepository;
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
        recipe.setName(recipeRequestDto.getRecipeName());
        recipe.setText(recipeRequestDto.getRecipeText());
        Set<Ingredient> ingredientSetList = new LinkedHashSet<>();

        try {
            recipe.setImage(recipeRequestDto.getImage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String urlFinki = "http://foodviz.env4health.finki.ukim.mk/predict?text="
                + recipeRequestDto.getRecipeText() + "&model=bioBert-standard-model-foodon-e100-0.0005.bin";

        TokensRequestDto tokensRequestDto = restTemplate.getForObject(urlFinki, TokensRequestDto.class);

        //Gi vadam koi se ingredients od od tekstot
        for (IngredientToken ingredientToken : Objects.requireNonNull(tokensRequestDto).getTokens()) {
            if (ingredientToken.getOtherTags() != null) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientToken.getWord());
                ingredientSetList.add(ingredient);
            }
        }
        if (ingredientSetList.isEmpty()) {
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
                ingredient.setFdcId(usdaResponseDto.getFoods().get(0).getFdcId());
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
        recipe.setProteinValue(0F);
        recipe.setCarbohydrateValue(0F);
        recipe.setFatValue(0F);
        recipe.setEnergyValue(0F);
        return recipeJpaRepository.save(recipe);
    }

    @Override
    public Set<RecipeIngredient> createRecipeIngredient(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = findByNameAndText(recipeRequestDto);
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipeId(recipe.getId());
            recipeIngredient.setIngredientName(ingredient.getName());
            recipeIngredient.setEnergy(0F);
            recipeIngredient.setFat(0F);
            recipeIngredient.setCarbohydrate(0F);
            recipeIngredient.setProtein(0F);
            recipeIngredientJpaRepository.save(recipeIngredient);
            recipeIngredients.add(recipeIngredient);
        }
        return recipeIngredients;
    }

    @Override
    public Recipe updateQuantity(RecipeRequestDto recipeRequestDto, String ingredientName, int quantity) {
        Recipe recipe = findByNameAndText(recipeRequestDto);
       // RecipeIngredient recipeIngredient = recipeIngredientJpaRepository.findRecipeIngredientByRecipeIdAndIngredientName(recipe.getId(), ingredientName);
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        if (recipeIngredientJpaRepository.findRecipeIngredientByRecipeIdAndIngredientName(recipe.getId(), ingredientName) != null) {
            recipeIngredient = recipeIngredientJpaRepository.findRecipeIngredientByRecipeIdAndIngredientName(recipe.getId(), ingredientName);
        }
        //change
        Ingredient ingredient = ingredientJpaRepository.getByName(ingredientName);
        if (recipe.getCarbohydrateValue() == null) {
            recipe.setCarbohydrateValue(ingredient.getCarbohydrate() / 100 * quantity);
            recipe.setEnergyValue(ingredient.getEnergy() / 100 * quantity);
            recipe.setFatValue(ingredient.getFat() / 100 * quantity);
            recipe.setProteinValue(ingredient.getProtein() / 100 * quantity);
        } else {
            float carbs = recipe.getCarbohydrateValue() - recipeIngredient.getCarbohydrate();
            float energy = recipe.getEnergyValue() - recipeIngredient.getEnergy();
            float fat = recipe.getFatValue() - recipeIngredient.getFat();
            float protein = recipe.getProteinValue() - recipeIngredient.getProtein();
            recipe.setCarbohydrateValue(carbs + ingredient.getCarbohydrate() / 100 * quantity);
            recipe.setEnergyValue(energy + ingredient.getEnergy() / 100 * quantity);
            recipe.setFatValue(fat + ingredient.getFat() / 100 * quantity);
            recipe.setProteinValue(protein + ingredient.getProtein() / 100 * quantity);
        }

        recipeIngredient.setRecipeId(recipe.getId());
        recipeIngredient.setIngredientName(ingredientName);
        recipeIngredient.setQuantity(quantity);
        recipeIngredient.setCarbohydrate(ingredient.getCarbohydrate() / 100 * quantity);
        recipeIngredient.setEnergy(ingredient.getEnergy() / 100 * quantity);
        recipeIngredient.setFat(ingredient.getFat() / 100 * quantity);
        recipeIngredient.setProtein(ingredient.getProtein() / 100 * quantity);
        recipeIngredientJpaRepository.save(recipeIngredient);
        recipeJpaRepository.save(recipe);
        return recipe;
    }

    @Override
    public List<RecipeIngredient> getAllIngredientQuantity(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = findByNameAndText(recipeRequestDto);
        return recipeIngredientJpaRepository.findAllByRecipeId(recipe.getId());
    }

    @Override
    public RecipeIngredient getIngredientQuantity(RecipeRequestDto recipeRequestDto, String ingredientName) {
        Recipe recipe = findByNameAndText(recipeRequestDto);
        return recipeIngredientJpaRepository.findRecipeIngredientByRecipeIdAndIngredientName(recipe.getId(), ingredientName);
    }

    @Override
    public Recipe findByNameAndText(RecipeRequestDto recipeRequestDto) {
        return recipeJpaRepository.findByNameAndText(recipeRequestDto.getRecipeName(), recipeRequestDto.getRecipeText());
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

    @Override
    public List<Recipe> findByCaloriesRange(Float minCalories,Float maxCalories) {
        return recipeJpaRepository.findAllByEnergyValueBetween(minCalories,maxCalories);
    }

    @Override
    public List<Recipe> findByFatRange(Float minFat, Float maxFat) {
        return recipeJpaRepository.findAllByFatValueBetween(minFat, maxFat);
    }

    @Override
    public List<Recipe> findByCarbsRange(Float minCarbs, Float maxCarbs) {
        return recipeJpaRepository.findAllByCarbohydrateValueBetween(minCarbs, maxCarbs);
    }

    @Override
    public List<Recipe> findByProteinRange(Float minProtein, Float maxProtein) {
        return recipeJpaRepository.findAllByProteinValueBetween(minProtein, maxProtein);
    }
}
