package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.OrderDetailDto;
import com.orel6505.restaurant.models.OrderDetail;
import com.orel6505.restaurant.models.Order;
import com.orel6505.restaurant.models.Dish;

public class OrderDetailMapper {

    private OrderDetailMapper() {
    }

    public static OrderDetailDto toDto(OrderDetail detail) {
        return new OrderDetailDto(
                detail.getId(),
                detail.getOrder() != null ? detail.getOrder().getId() : null,
                detail.getDish() != null ? detail.getDish().getId() : null,
                detail.getQuantity(),
                detail.getUnitPrice()
        );
    }

    public static OrderDetail toEntity(OrderDetailDto dto, Order order, Dish dish) {
        OrderDetail detail = new OrderDetail(
                order,
                dish,
                dto.quantity() != null ? dto.quantity() : 0,
                dto.unitPrice() != null ? dto.unitPrice() : 0.0
        );
        detail.setId(dto.id());
        return detail;
    }

    /**
     * Deprecated: use toEntity(OrderDetailDto, Order, Dish)
     */
    public static OrderDetail toEntity(OrderDetailDto dto) {
        throw new UnsupportedOperationException("Use toEntity(OrderDetailDto, Order, Dish)");
    }
}