package com.example.diplomska.repository;

import com.example.diplomska.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {
    //@Query(value = "SELECT recipe from Recipe recipe, Ingredient ingredient WHERE ingredient.name=:name AND ingredient IN (select r.ingredients from Recipe r)")
    @Query(value = "select recipe from Recipe recipe left join recipe.ingredients ingredients where ingredients.name=:name")
    List<Recipe> findAllByIngredient(@Param("name") String name);

    Recipe findByNameAndText(String name, String text);
}
