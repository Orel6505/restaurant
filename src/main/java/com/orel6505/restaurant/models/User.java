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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonProperty
    private java.util.Set<Role> roles = new java.util.HashSet<>();

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

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public User() {
        // Empty constructor
    }

    public User(String firstName, String lastName, int age, String address, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.password = password;
        this.orders = new java.util.ArrayList<>();
        this.roles = new java.util.HashSet<>();
    }

    public java.util.List<Order> getOrders() { return orders; }
    public void setOrders(java.util.List<Order> orders) { this.orders = orders; }

    public java.util.Set<Role> getRoles() { return roles; }
    public void setRoles(java.util.Set<Role> roles) { this.roles = roles; }

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

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
