package com.example.diplomska.rest;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.rest.converters.IngredientConverter;
import com.example.diplomska.rest.dto.IngredientRequestDto;
import com.example.diplomska.rest.dto.IngredientResponseDto;
import com.example.diplomska.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private IngredientService ingredientService;
    private IngredientConverter ingredientConverter;

    @GetMapping("")
    public List<Ingredient> getAll() {
        return ingredientService.getAll();
    }
/*    @GetMapping("/recipe/{id}")
    public List<Ingredient> getIngredientsInRecipe(@PathVariable Long id) {

        return ingredientService.findAllByRecipeId(id);
    }*/

    @PostMapping("")
    public IngredientResponseDto create(@RequestBody IngredientRequestDto ingredientRequestDto) {
        Ingredient ingredient = ingredientService.create(ingredientRequestDto.getName(),ingredientRequestDto.getUrl());
        return ingredientConverter.from(ingredient);
    }


}
