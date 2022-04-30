package com.example.diplomska.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@AllArgsConstructor
@Setter
@Getter
public class RecipeIngredients {
    @Id
    private long id;

}
