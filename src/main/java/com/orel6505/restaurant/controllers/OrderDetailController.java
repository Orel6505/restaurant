package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.OrderDetailDto;
import com.orel6505.restaurant.mappers.OrderDetailMapper;
import com.orel6505.restaurant.models.OrderDetail;
import com.orel6505.restaurant.services.JpqlQueryService;
import com.orel6505.restaurant.services.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final JpqlQueryService jpqlQueryService;

    public OrderDetailController(OrderDetailService orderDetailService, JpqlQueryService jpqlQueryService) {
        this.orderDetailService = orderDetailService;
        this.jpqlQueryService = jpqlQueryService;
    }

    @GetMapping
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll().stream().map(OrderDetailMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public OrderDetailDto getById(@PathVariable("id") int id) {
        return OrderDetailMapper.toDto(orderDetailService.getById(id));
    }

    @PostMapping
    public OrderDetailDto create(@RequestBody OrderDetailDto dto) {
        OrderDetail created = orderDetailService.create(OrderDetailMapper.toEntity(dto));
        return OrderDetailMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public OrderDetailDto update(@PathVariable("id") int id, @RequestBody OrderDetailDto dto) {
        OrderDetail updated = orderDetailService.update(id, OrderDetailMapper.toEntity(dto));
        return OrderDetailMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        orderDetailService.delete(id);
    }

    // Analytics Endpoint

    @GetMapping("/top-dishes")
    public List<Object[]> getTopOrderedDishes(@RequestParam(name = "limit", defaultValue = "10") int limit) {
        return jpqlQueryService.getTopOrderedDishes(limit);
    }
}