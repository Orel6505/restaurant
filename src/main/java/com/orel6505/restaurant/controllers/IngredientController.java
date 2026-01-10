package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.IngredientDto;
import com.orel6505.restaurant.mappers.IngredientMapper;
import com.orel6505.restaurant.models.Ingredient;
import com.orel6505.restaurant.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDto> getAll() {
        return ingredientService.getAll().stream().map(IngredientMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public IngredientDto getById(@PathVariable("id") int id) {
        return IngredientMapper.toDto(ingredientService.getById(id));
    }

    @GetMapping("/search")
    public List<IngredientDto> searchByName(@RequestParam("name") String name) {
        return ingredientService.searchByName(name).stream().map(IngredientMapper::toDto).toList();
    }

    @PostMapping
    public IngredientDto create(@RequestBody IngredientDto dto) {
        Ingredient created = ingredientService.create(IngredientMapper.toEntity(dto));
        return IngredientMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public IngredientDto update(@PathVariable("id") int id, @RequestBody IngredientDto dto) {
        Ingredient updated = ingredientService.update(id, IngredientMapper.toEntity(dto));
        return IngredientMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        ingredientService.delete(id);
    }
}