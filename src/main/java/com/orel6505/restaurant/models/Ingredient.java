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

    @JsonProperty
    @Column(nullable = false)
    private int dishId;

    @JsonProperty
    @Column(nullable = false)
    private String name;

    public Ingredient() {
        // Empty constructor
    }

    public Ingredient(int dishId, String name) {
        this.dishId = dishId;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public int getDishId() { return dishId; }
    public void setDishId(int dishId) { this.dishId = dishId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}