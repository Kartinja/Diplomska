package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@Getter
@Setter
public class RecipeRequestDto {
    private String recipeName;
    private String recipeText;
    private MultipartFile image;

}
