package com.example.diplomska.rest.dto;

import com.example.diplomska.repository.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class RecipeResponseDto {
    private long id;
    private String name;
    private String text;
    private String image;
    private Float fatValue;
    private Float proteinValue;
    private Float carbohydrateValue;
    private Float energyValue;

    private List<Ingredient> ingredients;
}
