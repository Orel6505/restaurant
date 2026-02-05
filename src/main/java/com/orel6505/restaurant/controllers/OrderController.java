package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.FullOrderDto;
import com.orel6505.restaurant.dto.OrderDto;
import com.orel6505.restaurant.mappers.OrderMapper;
import com.orel6505.restaurant.models.Order;
import com.orel6505.restaurant.services.JpqlQueryService;
import com.orel6505.restaurant.services.OrderQueryService;
import com.orel6505.restaurant.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Orders", description = "Order Management API")
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
    @Operation(summary = "Get all orders", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<OrderDto> getAll() {
        return orderService.getAll().stream().map(OrderMapper::toDto).toList();
    }

    @GetMapping("/{id}/full")
    @Operation(summary = "Get full order details", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public FullOrderDto getFullOrder(@PathVariable("id") int id) {
        return orderQueryService.getFullOrder(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public OrderDto getById(@PathVariable("id") int id) {
        return OrderMapper.toDto(orderService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new order", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public OrderDto create(@RequestBody OrderDto dto) {
        Order created = orderService.create(OrderMapper.toEntity(dto));
        return OrderMapper.toDto(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public OrderDto update(@PathVariable("id") int id, @RequestBody OrderDto dto) {
        Order updated = orderService.update(id, OrderMapper.toEntity(dto));
        return OrderMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void delete(@PathVariable("id") int id) {
        orderService.delete(id);
    }

    // Analytics Endpoints

    @GetMapping("/price-range")
    @Operation(summary = "Get orders by price range", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<OrderDto> getOrdersByPriceRange(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice) {
        return jpqlQueryService.getOrdersByPriceRange(minPrice, maxPrice);
    }
}