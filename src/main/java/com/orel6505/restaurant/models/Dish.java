package com.orel6505.restaurant.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "dish")
public class Dish {
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty
    private java.util.List<Ingredient> ingredients = new java.util.ArrayList<>();

    public java.util.List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(java.util.List<Ingredient> ingredients) { this.ingredients = ingredients; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @JsonProperty
    @Column(nullable = false)
    private String name;

    @JsonProperty
    @Column(nullable = false)
    private double price;

    public Dish() {
        // Empty constructor
    }

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}