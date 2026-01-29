package com.orel6505.restaurant.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;

@Entity
@JsonSerialize
@Table(name = "users")
public class User {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty
    private java.util.List<Order> orders = new java.util.ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @JsonProperty
    @Column(nullable = false)
    private String firstName;

    @JsonProperty
    @Column(nullable = false)
    private String lastName;

    @JsonProperty
    @Column
    private int age;

    @JsonProperty
    @Column
    private String address;

    @JsonProperty
    @Column
    private String email;

    public User() {
        // Empty constructor
    }

    public User(String firstName, String lastName, int age, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.orders = new java.util.ArrayList<>();
    }

    public java.util.List<Order> getOrders() { return orders; }
    public void setOrders(java.util.List<Order> orders) { this.orders = orders; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}