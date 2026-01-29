package com.orel6505.restaurant.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;

@Entity
@JsonSerialize
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @JsonBackReference
    private Dish dish;

    @JsonProperty
    @Column(nullable = false)
    private String name;

    public Ingredient() {
        // Empty constructor
    }

    public Ingredient(Dish dish, String name) {
        this.dish = dish;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Dish getDish() { return dish; }
    public void setDish(Dish dish) { this.dish = dish; }
    // Removed getDishId()

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}