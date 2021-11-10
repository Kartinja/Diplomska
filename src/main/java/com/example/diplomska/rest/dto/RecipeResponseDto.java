package com.example.diplomska.rest.dto;

import com.example.diplomska.repository.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class RecipeResponseDto {
    private long id;
    private String name;
    private String text;
    private List<Ingredient> ingredients;
}
