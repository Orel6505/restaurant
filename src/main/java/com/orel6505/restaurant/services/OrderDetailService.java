package com.orel6505.restaurant.services;

import com.orel6505.restaurant.models.OrderDetail;
import com.orel6505.restaurant.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail getById(int id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found: " + id));
    }

    public List<OrderDetail> getByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public List<OrderDetail> getByDishId(int dishId) {
        return orderDetailRepository.findByDishId(dishId);
    }

    public OrderDetail create(OrderDetail detail) {
        detail.setId(0);
        return orderDetailRepository.save(detail);
    }

    public OrderDetail update(int id, OrderDetail updated) {
        OrderDetail existing = getById(id);
        existing.setOrderId(updated.getOrderId());
        existing.setDishId(updated.getDishId());
        existing.setQuantity(updated.getQuantity());
        existing.setUnitPrice(updated.getUnitPrice());
        return orderDetailRepository.save(existing);
    }

    public void delete(int id) {
        OrderDetail existing = getById(id);
        orderDetailRepository.delete(existing);
    }
}