package com.example.diplomska.rest;

import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/resources")
@AllArgsConstructor
public class ResourcesController {

    private RecipeService recipeService;

//    @GetMapping()
//    public Byte[] resource() {
//        Recipe recipe = recipeService.get(1);
//        return recipe.getImage();
//    }

}
