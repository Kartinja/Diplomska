package com.example.diplomska.repository;

import com.example.diplomska.repository.model.Ingredient;
import com.example.diplomska.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeJpaRepository extends JpaRepository<Recipe,Long> {

}
