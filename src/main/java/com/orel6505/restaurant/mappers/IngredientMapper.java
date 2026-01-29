package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.IngredientDto;
import com.orel6505.restaurant.models.Ingredient;

public class IngredientMapper {

    private IngredientMapper() {
    }

    public static IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getDish() != null ? ingredient.getDish().getId() : null, ingredient.getName());
    }

    // This method now requires a Dish object, not just dishId
    public static Ingredient toEntity(IngredientDto dto, com.orel6505.restaurant.models.Dish dish) {
        Ingredient ingredient = new Ingredient(dish, dto.name());
        ingredient.setId(dto.id());
        return ingredient;
    }

    /**
     * Deprecated: use toEntity(IngredientDto, Dish)
     */
    public static Ingredient toEntity(IngredientDto dto) {
        throw new UnsupportedOperationException("Use toEntity(IngredientDto, Dish)");
    }
}