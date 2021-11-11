package com.example.diplomska.rest;

import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.rest.converters.RecipeConverter;
import com.example.diplomska.rest.dto.RecipeRequestDto;
import com.example.diplomska.rest.dto.RecipeResponseDto;
import com.example.diplomska.service.IngredientService;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;
    private RecipeConverter recipeConverter;

    @GetMapping("")
    public List<RecipeResponseDto> getAll(){
        return recipeService.getAll().stream().map(recipeConverter::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RecipeResponseDto getRecipe(@PathVariable long id){
        Recipe recipe = recipeService.get(id);
        return recipeConverter.from(recipe);
    }

    @PostMapping("")
    public RecipeResponseDto create(@RequestBody RecipeRequestDto recipeRequestDto){
        Recipe recipe = recipeService.create(recipeRequestDto.getName(), recipeRequestDto.getText(),recipeRequestDto.getIngredients());

        return recipeConverter.from(recipe);
    }

    @DeleteMapping("/{id}")
    public RecipeResponseDto delete(@PathVariable long id){
        Recipe recipe = recipeService.delete(id);
        return recipeConverter.from(recipe);
    }
}