package com.orel6505.restaurant.dto;

public record OrderDetailDto(Integer id, Integer orderId, Integer dishId, Integer quantity, Double unitPrice) {}