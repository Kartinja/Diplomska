package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecipeIngredientResponseDto {
    private String ingredientName;
    private String recipeName;
    private int ingredientQuantity;
    private Float protein;
    private Float fat;
    private Float carbohydrate;
    private Float energy;

}
