package com.example.diplomska.repository;

import com.example.diplomska.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "select recipe from Recipe recipe left join recipe.ingredients ingredients where ingredients.name=:name")
    List<Recipe> findAllByIngredient(@Param("name") String name);
    List<Recipe> findAllByEnergyValueBetween(Float minCalories, Float maxCalories);
    List<Recipe> findAllByProteinValueBetween(Float minProtein, Float maxProtein);
    List<Recipe> findAllByCarbohydrateValueBetween(Float minCarbs, Float maxCarbs);
    List<Recipe> findAllByFatValueBetween(Float minFat, Float maxFat);

    Recipe findByNameAndText(String name, String text);
}