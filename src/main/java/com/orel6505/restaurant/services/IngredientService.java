package com.orel6505.restaurant.services;

import com.orel6505.restaurant.models.Ingredient;
import com.orel6505.restaurant.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found: " + id));
    }

    public List<Ingredient> getByDishId(int dishId) {
        return ingredientRepository.findByDishId(dishId);
    }

    public List<Ingredient> searchByName(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }

    public Ingredient create(Ingredient ingredient) {
        ingredient.setId(0);
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(int id, Ingredient updated) {
        Ingredient existing = getById(id);
        existing.setDishId(updated.getDishId());
        existing.setName(updated.getName());
        return ingredientRepository.save(existing);
    }

    public void delete(int id) {
        Ingredient existing = getById(id);
        ingredientRepository.delete(existing);
    }
}