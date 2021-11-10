package com.example.diplomska.rest.dto;

import com.example.diplomska.repository.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RecipeRequestDto {
    private String name;
    private String text;
    private List<Ingredient> ingredients;
}
