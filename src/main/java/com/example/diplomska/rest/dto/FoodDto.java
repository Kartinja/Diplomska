package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Food {
    int fdcId;
    String description;
    String lowercaseDescription;
    String dataType;
    String gtinUpc;
    String publishedDate;
    String brandOwner;
    String ingredients;
    String marketCountry;
    String foodCategory;
    String modifiedDate;
    String dataSource;
    String servingSizeUnit;
    int servingSize;
    String householdServingFullText;
    String allHighlightFields;
    int score;
    List<FoodNutrient> foodNutrients;
    List<Object> finalFoodInputFoods;
    List<Object> foodMeasures;
    List<Object> foodAttributes;
    List<Object> foodAttributeTypes;
    List<Object> foodNutrients;
}
