package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.OrderDetailDto;
import com.orel6505.restaurant.mappers.OrderDetailMapper;
import com.orel6505.restaurant.services.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
public class OrderItemController {

    private final OrderDetailService orderDetailService;

    public OrderItemController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public List<OrderDetailDto> getOrderItems(@PathVariable("orderId") int orderId) {
        return orderDetailService.getByOrderId(orderId).stream()
                .map(OrderDetailMapper::toDto).toList();
    }
}
