package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.DishDto;
import com.orel6505.restaurant.mappers.DishMapper;
import com.orel6505.restaurant.models.Dish;
import com.orel6505.restaurant.services.DishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<DishDto> getAll() {
        return dishService.getAll().stream().map(DishMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public DishDto getById(@PathVariable("id") int id) {
        return DishMapper.toDto(dishService.getById(id));
    }

    @GetMapping("/search")
    public List<DishDto> searchByName(@RequestParam("name") String name) {
        return dishService.searchByName(name).stream().map(DishMapper::toDto).toList();
    }

    @PostMapping
    public DishDto create(@RequestBody DishDto dto) {
        Dish created = dishService.create(DishMapper.toEntity(dto));
        return DishMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public DishDto update(@PathVariable("id") int id, @RequestBody DishDto dto) {
        Dish updated = dishService.update(id, DishMapper.toEntity(dto));
        return DishMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        dishService.delete(id);
    }
}