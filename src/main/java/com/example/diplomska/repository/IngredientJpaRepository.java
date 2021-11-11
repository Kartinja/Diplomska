package com.example.diplomska.repository;

import com.example.diplomska.repository.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {
    Ingredient getByName(String name);
    boolean existsByName(String name);
}
