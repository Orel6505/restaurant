package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.IngredientDto;
import com.orel6505.restaurant.mappers.IngredientMapper;
import com.orel6505.restaurant.models.Ingredient;
import com.orel6505.restaurant.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Ingredients", description = "Ingredient Management API")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @Operation(summary = "Get all ingredients", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<IngredientDto> getAll() {
        return ingredientService.getAll().stream().map(IngredientMapper::toDto).toList();
    }

    @GetMapping("/search")
    @Operation(summary = "Search ingredients by name", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<IngredientDto> searchByName(@RequestParam("name") String name) {
        return ingredientService.searchByName(name).stream().map(IngredientMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ingredient by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public IngredientDto getById(@PathVariable("id") int id) {
        return IngredientMapper.toDto(ingredientService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new ingredient", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public IngredientDto create(@RequestBody IngredientDto dto) {
        Ingredient created = ingredientService.create(IngredientMapper.toEntity(dto));
        return IngredientMapper.toDto(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update ingredient", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public IngredientDto update(@PathVariable("id") int id, @RequestBody IngredientDto dto) {
        Ingredient updated = ingredientService.update(id, IngredientMapper.toEntity(dto));
        return IngredientMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ingredient", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void delete(@PathVariable("id") int id) {
        ingredientService.delete(id);
    }
}