package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.IngredientDto;
import com.orel6505.restaurant.models.Ingredient;

public class IngredientMapper {

    private IngredientMapper() {
    }

    public static IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getDishId(), ingredient.getName());
    }

    public static Ingredient toEntity(IngredientDto dto) {
        Ingredient ingredient = new Ingredient(dto.dishId(), dto.name());
        ingredient.setId(dto.id());
        return ingredient;
    }
}