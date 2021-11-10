package com.example.diplomska.repository;

import com.example.diplomska.repository.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {
    Ingredient getByName(String name);
    List<Ingredient> findAllByRecipeId(long id);
    boolean existsByName(String name);
}
