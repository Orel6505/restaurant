package com.orel6505.restaurant.repositories;

import com.orel6505.restaurant.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByDishId(int dishId);
    List<Ingredient> findByNameContainingIgnoreCase(String name);
}