package com.orel6505.restaurant.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;

@Entity
@JsonSerialize
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @JsonProperty
    @Column(nullable = false)
    private int orderId;

    @JsonProperty
    @Column(nullable = false)
    private int dishId;

    @JsonProperty
    @Column(nullable = false)
    private int quantity;

    @JsonProperty
    @Column(nullable = false)
    private double unitPrice;

    public OrderDetail() {
        // Empty constructor
    }

    public OrderDetail(int orderId, int dishId, int quantity, double unitPrice) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getDishId() { return dishId; }
    public void setDishId(int dishId) { this.dishId = dishId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}