package com.example.diplomska.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
public class RecipeIngredient {
    @Id
    @GeneratedValue
    private long id;
    private long recipeId;
    private String ingredientName;
    private Float protein;
    private Float fat;
    private Float carbohydrate;
    private Float energy;
    private int quantity;

}

