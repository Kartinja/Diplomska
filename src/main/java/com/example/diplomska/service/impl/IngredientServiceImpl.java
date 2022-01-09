package com.example.diplomska.service.impl;

import com.example.diplomska.repository.IngredientJpaRepository;
import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.rest.dto.FoodNutrientDto;
import com.example.diplomska.rest.dto.UsdaResponseDto;
import com.example.diplomska.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientJpaRepository ingredientJpaRepository;

    public List<Ingredient> getAll() {
        return ingredientJpaRepository.findAll();
    }

    public Ingredient post(UsdaResponseDto usdaResponseDto, String name) {
        Ingredient ingredient = ingredientJpaRepository.getByName(name);
        List<FoodNutrientDto> foodNutrients = usdaResponseDto.getFoods().get(0).getFoodNutrients();
        /*for (FoodNutrientDto foodNutrient:foodNutrients) {
            if(foodNutrient.getNutrientName().toLowerCase().contains("protein")){
                ingredient.setProtein(foodNutrient.getValue());
            }
            if(foodNutrient.getNutrientName().toLowerCase(Locale.ROOT).contains("carbohydrate")){
                ingredient.setCarbohydrate(foodNutrient.getValue());
            }
            if(foodNutrient.getNutrientName().toLowerCase(Locale.ROOT).contains("fat")){
                ingredient.setFat(foodNutrient.getValue());
            }
            if(foodNutrient.getNutrientName().toLowerCase(Locale.ROOT).contains("energy")){
                ingredient.setEnergy(foodNutrient.getValue());
            }
        }*/

        return ingredientJpaRepository.save(ingredient);
    }


    @Override
    public Ingredient get(String name) {
        return ingredientJpaRepository.getByName(name);
    }


}
