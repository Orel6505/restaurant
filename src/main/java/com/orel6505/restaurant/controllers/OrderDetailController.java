package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.OrderDetailDto;
import com.orel6505.restaurant.mappers.OrderDetailMapper;
import com.orel6505.restaurant.models.OrderDetail;
import com.orel6505.restaurant.services.JpqlQueryService;
import com.orel6505.restaurant.services.OrderDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final JpqlQueryService jpqlQueryService;

    public OrderDetailController(OrderDetailService orderDetailService, JpqlQueryService jpqlQueryService) {
        this.orderDetailService = orderDetailService;
        this.jpqlQueryService = jpqlQueryService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll().stream().map(OrderDetailMapper::toDto).toList();
    }

    @GetMapping("/top-dishes")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<Object[]> getTopDishes(@RequestParam(name = "limit", defaultValue = "10") int limit) {
        return jpqlQueryService.getTopOrderedDishes(limit);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public OrderDetailDto getById(@PathVariable("id") int id) {
        return OrderDetailMapper.toDto(orderDetailService.getById(id));
    }
    public List<Object[]> getTopOrderedDishes(@RequestParam(name = "limit", defaultValue = "10") int limit) {
        return jpqlQueryService.getTopOrderedDishes(limit);
    }
}