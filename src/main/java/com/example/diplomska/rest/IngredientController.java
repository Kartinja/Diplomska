package com.example.diplomska.rest;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.rest.converters.IngredientConverter;
import com.example.diplomska.rest.dto.FoodNutrientDto;
import com.example.diplomska.rest.dto.IngredientResponseDto;
import com.example.diplomska.rest.dto.UsdaResponseDto;
import com.example.diplomska.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private IngredientService ingredientService;
    private IngredientConverter ingredientConverter;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("")
    public List<Ingredient> getAll() {
        return ingredientService.getAll();
    }

    @GetMapping("/{name}")
    public IngredientResponseDto get(@PathVariable String name) {
        Ingredient ingredient = ingredientService.get(name);
        if (ingredient.getEnergy() == null && ingredient.getCarbohydrate() == null && ingredient.getFat() == null && ingredient.getProtein() == null) {
            String url = "https://api.nal.usda.gov/fdc/v1/foods/search?query=" + name + "&pageSize=2&api_key=pfmU6uLGv9pUvJz9oocLidkY8cKDxLsV27c9cn7t";
            UsdaResponseDto usdaResponseDto = webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(UsdaResponseDto.class).block();
            assert usdaResponseDto != null;
            List<FoodNutrientDto> foodNutrients = usdaResponseDto.getFoods().get(0).getFoodNutrients();

            ingredient=ingredientService.post(usdaResponseDto, name);

        }
        return ingredientConverter.from(ingredient);
    }

    /*@PostMapping("")
    public IngredientResponseDto create(@RequestBody IngredientRequestDto ingredientRequestDto) {
        Ingredient ingredient = ingredientService.create(ingredientRequestDto.getName(),ingredientRequestDto.getUrl());
        return ingredientConverter.from(ingredient);
    }*/


}
