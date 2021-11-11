package com.example.diplomska.service.impl;

import com.example.diplomska.repository.IngredientJpaRepository;
import com.example.diplomska.repository.RecipeJpaRepository;
import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import com.example.diplomska.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientJpaRepository ingredientJpaRepository;

    public List<Ingredient> getAll() {
        return ingredientJpaRepository.findAll();
    }

    public Ingredient create(String name,String url){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setUrl(url);
        return ingredientJpaRepository.save(ingredient);
    }


    @Override
    public Ingredient get(long id) {
        return ingredientJpaRepository.getById(id);
    }

    /*@Override
    public List<Ingredient> findAllByRecipeId(Long id) {
        return ingredientJpaRepository.findAllByRecipeId(id);
    }*/

}
