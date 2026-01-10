package com.orel6505.restaurant.repositories;

import com.orel6505.restaurant.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findByNameContainingIgnoreCase(String name);
}