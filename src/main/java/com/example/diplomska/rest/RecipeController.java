package com.example.diplomska.rest;

import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.repository.model.RecipeIngredient;
import com.example.diplomska.rest.converters.RecipeConverter;
import com.example.diplomska.rest.converters.RecipeIngredientConverter;
import com.example.diplomska.rest.dto.RecipeIngredientResponseDto;
import com.example.diplomska.rest.dto.RecipeRequestDto;
import com.example.diplomska.rest.dto.RecipeResponseDto;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;
    private RecipeConverter recipeConverter;
    private RecipeIngredientConverter recipeIngredientConverter;

    @GetMapping("")
    public List<RecipeResponseDto> getAll() {
        return recipeService.getAll().stream().map(recipeConverter::from).collect(Collectors.toList());
    }

    @PostMapping("")
    public RecipeResponseDto create(@ModelAttribute RecipeRequestDto recipeRequestDto) throws IOException {
        Recipe recipe = recipeService.create(recipeRequestDto);
        recipeService.createRecipeIngredient(recipeRequestDto);
        return recipeConverter.from(recipe);
    }

    @GetMapping("/{ingredientName}")
    public List<RecipeResponseDto> getByIngredientName(@PathVariable String ingredientName) {
        return recipeService.findByIngredient(ingredientName).stream().map(recipeConverter::from).collect(Collectors.toList());
    }

    @GetMapping("/calories/{minCalories}/{maxCalories}")
    public List<RecipeResponseDto> getByCalories(@PathVariable Float minCalories, @PathVariable Float maxCalories) {
        return recipeService.findByCaloriesRange(minCalories,maxCalories).stream().map(recipeConverter::from).collect(Collectors.toList());
    }

    @GetMapping("/carbohydrate/{minCarbs}/{maxCarbs}")
    public List<RecipeResponseDto> getByCarbs(@PathVariable Float minCarbs, @PathVariable Float maxCarbs) {
        return recipeService.findByCarbsRange(minCarbs,maxCarbs).stream().map(recipeConverter::from).collect(Collectors.toList());
    }
    @GetMapping("/fat/{minFat}/{maxFat}")
    public List<RecipeResponseDto> getByFat(@PathVariable Float minFat, @PathVariable Float maxFat) {
        return recipeService.findByFatRange(minFat,maxFat).stream().map(recipeConverter::from).collect(Collectors.toList());
    }
    @GetMapping("/protein/{minProtein}/{maxProtein}")
    public List<RecipeResponseDto> getByProtein(@PathVariable Float minProtein, @PathVariable Float maxProtein) {
        return recipeService.findByProteinRange(minProtein,maxProtein).stream().map(recipeConverter::from).collect(Collectors.toList());
    }


    @GetMapping("/by")
    public RecipeResponseDto getByNameAndText(@ModelAttribute RecipeRequestDto recipeRequestDto) {
        Recipe recipe = recipeService.findByNameAndText(recipeRequestDto);
        return recipeConverter.from(recipe);
    }

    @PostMapping("/quantity")
    public RecipeResponseDto updateQuantity(@ModelAttribute RecipeRequestDto recipeRequestDto, String ingredientName, int quantity) {
        return recipeConverter.from(recipeService.updateQuantity(recipeRequestDto, ingredientName, quantity));
    }

    @GetMapping("/quantity")
    public RecipeIngredientResponseDto getRecipeIngredientQuantity(@ModelAttribute RecipeRequestDto recipeRequestDto, String ingredientName) {
        RecipeIngredient recipeIngredient
                = recipeService.getIngredientQuantity(recipeRequestDto, ingredientName);

        return recipeIngredientConverter.from(recipeIngredient, recipeRequestDto.getRecipeName());
    }


    @GetMapping("/quantities")
    public List<RecipeIngredientResponseDto> getAllRecipeIngredientQuantities(@ModelAttribute RecipeRequestDto recipeRequestDto) {
        List<RecipeIngredient> allIngredientQuantity = recipeService.getAllIngredientQuantity(recipeRequestDto);
        List<RecipeIngredientResponseDto> recipeIngredientResponseDto = new ArrayList<>();
        for (RecipeIngredient recipeIngredient : allIngredientQuantity) {
            recipeIngredientResponseDto.add(recipeIngredientConverter.from(recipeIngredient, recipeRequestDto.getRecipeName()));
        }

        return recipeIngredientResponseDto;
    }

    @DeleteMapping("/{id}")
    public RecipeResponseDto delete(@PathVariable long id) {
        Recipe recipe = recipeService.delete(id);
        return recipeConverter.from(recipe);
    }
}
