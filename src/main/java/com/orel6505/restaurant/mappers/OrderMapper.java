package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.OrderDto;
import com.orel6505.restaurant.models.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getUserId(), order.getTotalPrice());
    }

    public static Order toEntity(OrderDto dto) {
        Order order = new Order(dto.userId(), dto.totalPrice());
        order.setId(dto.id());
        return order;
    }
}