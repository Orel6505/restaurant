package com.orel6505.restaurant.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import java.time.LocalDateTime;

@Entity
@JsonSerialize
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @JsonProperty
    @Column(nullable = false)
    private int userId;

    @JsonProperty
    @Column(nullable = false)
    private double totalPrice;

    @JsonProperty
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order() {
        // Empty constructor
    }

    public Order(int userId, double totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}