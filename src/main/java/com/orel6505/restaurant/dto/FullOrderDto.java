package com.orel6505.restaurant.dto;

import java.util.List;

public record FullOrderDto(int orderId, int userId, double totalPrice, List<OrderItemDto> items) {}