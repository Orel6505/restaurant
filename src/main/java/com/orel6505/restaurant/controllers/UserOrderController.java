package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.OrderDto;
import com.orel6505.restaurant.mappers.OrderMapper;
import com.orel6505.restaurant.services.JpqlQueryService;
import com.orel6505.restaurant.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/orders")
public class UserOrderController {

    private final OrderService orderService;
    private final JpqlQueryService jpqlQueryService;

    public UserOrderController(OrderService orderService, JpqlQueryService jpqlQueryService) {
        this.orderService = orderService;
        this.jpqlQueryService = jpqlQueryService;
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@PathVariable("userId") int userId) {
        return orderService.getByUserId(userId).stream().map(OrderMapper::toDto).toList();
    }

    @GetMapping("/total-spent")
    public Double getTotalSpentByUser(@PathVariable("userId") int userId) {
        return jpqlQueryService.getTotalSpentByUser(userId);
    }

    @GetMapping("/average-spent")
    public Double getAverageOrderValueByUser(@PathVariable("userId") int userId) {
        return jpqlQueryService.getAverageOrderValueByUser(userId);
    }

    @GetMapping("/statistics")
    public Object[] getUserStatistics(@PathVariable("userId") int userId) {
        return jpqlQueryService.getUserStatistics(userId);
    }
}
