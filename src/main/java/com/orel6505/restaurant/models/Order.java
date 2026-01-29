package com.orel6505.restaurant.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import java.time.LocalDateTime;

@Entity
@JsonSerialize
@Table(name = "orders")
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty
    private java.util.List<OrderDetail> orderDetails = new java.util.ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @JsonProperty
    @Column(nullable = false)
    private double totalPrice;

    @JsonProperty
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order() {
        // Empty constructor
    }

    public Order(User user, double totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderDetails = new java.util.ArrayList<>();
    }

    public java.util.List<OrderDetail> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(java.util.List<OrderDetail> orderDetails) { this.orderDetails = orderDetails; }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    // Removed getUserId()
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}