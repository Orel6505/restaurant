package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.FullOrderDto;
import com.orel6505.restaurant.dto.OrderDto;
import com.orel6505.restaurant.mappers.OrderMapper;
import com.orel6505.restaurant.models.Order;
import com.orel6505.restaurant.services.JpqlQueryService;
import com.orel6505.restaurant.services.OrderQueryService;
import com.orel6505.restaurant.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;
    private final JpqlQueryService jpqlQueryService;

    public OrderController(OrderService orderService, OrderQueryService orderQueryService, JpqlQueryService jpqlQueryService) {
        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
        this.jpqlQueryService = jpqlQueryService;
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll().stream().map(OrderMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable("id") int id) {
        return OrderMapper.toDto(orderService.getById(id));
    }

    @GetMapping("/{id}/full")
    public FullOrderDto getFullOrder(@PathVariable("id") int id) {
        return orderQueryService.getFullOrder(id);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto dto) {
        Order created = orderService.create(OrderMapper.toEntity(dto));
        return OrderMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable("id") int id, @RequestBody OrderDto dto) {
        Order updated = orderService.update(id, OrderMapper.toEntity(dto));
        return OrderMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        orderService.delete(id);
    }

    // Analytics Endpoints

    @GetMapping("/price-range")
    public List<OrderDto> getOrdersByPriceRange(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice) {
        return jpqlQueryService.getOrdersByPriceRange(minPrice, maxPrice);
    }
}