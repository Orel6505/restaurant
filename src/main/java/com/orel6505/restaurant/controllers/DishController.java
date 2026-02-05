package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.DishDto;
import com.orel6505.restaurant.mappers.DishMapper;
import com.orel6505.restaurant.models.Dish;
import com.orel6505.restaurant.services.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Dishes", description = "Dish Management API")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    @Operation(summary = "Get all dishes", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<DishDto> getAll() {
        return dishService.getAll().stream().map(DishMapper::toDto).toList();
    }

    @GetMapping("/search")
    @Operation(summary = "Search dishes by name", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<DishDto> searchByName(@RequestParam("name") String name) {
        return dishService.searchByName(name).stream().map(DishMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public DishDto getById(@PathVariable("id") int id) {
        return DishMapper.toDto(dishService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new dish", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public DishDto create(@RequestBody DishDto dto) {
        Dish created = dishService.create(DishMapper.toEntity(dto));
        return DishMapper.toDto(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update dish", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public DishDto update(@PathVariable("id") int id, @RequestBody DishDto dto) {
        Dish updated = dishService.update(id, DishMapper.toEntity(dto));
        return DishMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete dish", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void delete(@PathVariable("id") int id) {
        dishService.delete(id);
    }
}