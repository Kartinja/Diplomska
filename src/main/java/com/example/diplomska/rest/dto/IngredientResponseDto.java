package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IngredientResponseDto {
    private String name;
    private Float protein;
    private Float fat;
    private Float Carbohydrate;
    private Float Energy;

}
