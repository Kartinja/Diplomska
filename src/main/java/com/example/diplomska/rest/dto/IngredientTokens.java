package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class IngredientTokens {
    private int numTokens;
    private List<Object> otherTags;
    private boolean removed;
    private int start;
    private String tag;
    private boolean updated;
    private String word;
}
