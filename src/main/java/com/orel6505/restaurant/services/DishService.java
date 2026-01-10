package com.orel6505.restaurant.services;

import com.orel6505.restaurant.models.Dish;
import com.orel6505.restaurant.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish getById(int id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found: " + id));
    }

    public List<Dish> searchByName(String name) {
        return dishRepository.findByNameContainingIgnoreCase(name);
    }

    public Dish create(Dish dish) {
        dish.setId(0);
        return dishRepository.save(dish);
    }

    public Dish update(int id, Dish updated) {
        Dish existing = getById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return dishRepository.save(existing);
    }

    public void delete(int id) {
        Dish existing = getById(id);
        dishRepository.delete(existing);
    }
}