package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.DishDto;
import com.orel6505.restaurant.models.Dish;

public class DishMapper {

    private DishMapper() {
    }

    public static DishDto toDto(Dish dish) {
        return new DishDto(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static Dish toEntity(DishDto dto) {
        Dish dish = new Dish(dto.name(), dto.price());
        dish.setId(dto.id());
        return dish;
    }
}