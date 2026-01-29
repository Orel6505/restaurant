package com.orel6505.restaurant.services;

import com.orel6505.restaurant.dto.FullOrderDto;
import com.orel6505.restaurant.dto.OrderItemDto;
import com.orel6505.restaurant.models.Dish;
import com.orel6505.restaurant.models.Order;
import com.orel6505.restaurant.models.OrderDetail;
import com.orel6505.restaurant.repositories.DishRepository;
import com.orel6505.restaurant.repositories.OrderDetailRepository;
import com.orel6505.restaurant.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final DishRepository dishRepository;

    public OrderQueryService(OrderRepository orderRepository,
                             OrderDetailRepository orderDetailRepository,
                             DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.dishRepository = dishRepository;
    }

    public FullOrderDto getFullOrder(int orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        List<OrderDetail> details = orderDetailRepository.findByOrderId(orderId);

        List<OrderItemDto> items = new ArrayList<>();
        for (OrderDetail d : details) {
            Dish dish = d.getDish();
            if (dish == null) {
            throw new RuntimeException("Dish not found for OrderDetail: " + d.getId());
            }
            items.add(new OrderItemDto(dish.getId(), dish.getName(), dish.getPrice()));
        }

        return new FullOrderDto(order.getId(), order.getUser() != null ? order.getUser().getId() : null, order.getTotalPrice(), items);
    }
}