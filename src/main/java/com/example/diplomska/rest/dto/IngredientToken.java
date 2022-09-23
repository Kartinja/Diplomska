package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IngredientToken {
    private int numTokens;
    private OtherTagsDto otherTags;
    private Object removed;
    private int start;
    private String tag;
    private Object updated;
    private String word;
}
