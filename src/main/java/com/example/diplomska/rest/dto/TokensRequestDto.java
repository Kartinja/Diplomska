package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class TokensRequestDto {
    private List<IngredientTokens> ingredientTokensList;
}
