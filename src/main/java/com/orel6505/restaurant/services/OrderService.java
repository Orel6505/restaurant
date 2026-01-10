package com.orel6505.restaurant.services;

import com.orel6505.restaurant.models.Order;
import com.orel6505.restaurant.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    public List<Order> getByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order create(Order order) {
        order.setId(0);
        return orderRepository.save(order);
    }

    public Order update(int id, Order updated) {
        Order existing = getById(id);
        existing.setUserId(updated.getUserId());
        existing.setTotalPrice(updated.getTotalPrice());
        return orderRepository.save(existing);
    }

    public void delete(int id) {
        Order existing = getById(id);
        orderRepository.delete(existing);
    }
}