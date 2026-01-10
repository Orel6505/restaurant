package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.IngredientDto;
import com.orel6505.restaurant.mappers.IngredientMapper;
import com.orel6505.restaurant.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes/{dishId}/ingredients")
public class DishIngredientController {

    private final IngredientService ingredientService;

    public DishIngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDto> getIngredientsByDish(@PathVariable("dishId") int dishId) {
        return ingredientService.getByDishId(dishId).stream()
                .map(IngredientMapper::toDto).toList();
    }
}
