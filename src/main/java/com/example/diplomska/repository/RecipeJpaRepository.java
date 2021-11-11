package com.example.diplomska.repository;

import com.example.diplomska.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {

}
