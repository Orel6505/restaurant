package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.OrderDto;
import com.orel6505.restaurant.models.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getUser() != null ? order.getUser().getId() : null, order.getTotalPrice());
    }

    // This method now requires a User object, not just userId
    public static Order toEntity(OrderDto dto, com.orel6505.restaurant.models.User user) {
        Order order = new Order(user, dto.totalPrice());
        order.setId(dto.id());
        return order;
    }

    /**
     * Deprecated: use toEntity(OrderDto, User)
     */
    public static Order toEntity(OrderDto dto) {
        throw new UnsupportedOperationException("Use toEntity(OrderDto, User)");
    }
}