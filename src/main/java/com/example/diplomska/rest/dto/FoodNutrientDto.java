package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FoodNutrientDto {
    int nutrientId;
    String nutrientName;
    String nutrientNumber;
    String unitName;
    String derivationCode;
    String derivationDescription;
    int derivationId;
    Float value;
    int foodNutrientSourceId;
    String foodNutrientSourceCode;
    String foodNutrientSourceDescription;
    int rank;
    int indentLevel;
    int foodNutrientId;
    int percentDailyValue;
}
