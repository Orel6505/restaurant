package com.orel6505.restaurant.mappers;

import com.orel6505.restaurant.dto.OrderDetailDto;
import com.orel6505.restaurant.models.OrderDetail;

public class OrderDetailMapper {

    private OrderDetailMapper() {
    }

    public static OrderDetailDto toDto(OrderDetail detail) {
        return new OrderDetailDto(
                detail.getId(),
                detail.getOrderId(),
                detail.getDishId(),
                detail.getQuantity(),
                detail.getUnitPrice()
        );
    }

    public static OrderDetail toEntity(OrderDetailDto dto) {
        OrderDetail detail = new OrderDetail(
                dto.orderId(),
                dto.dishId(),
                dto.quantity() != null ? dto.quantity() : 0,
                dto.unitPrice() != null ? dto.unitPrice() : 0.0
        );
        detail.setId(dto.id());
        return detail;
    }
}