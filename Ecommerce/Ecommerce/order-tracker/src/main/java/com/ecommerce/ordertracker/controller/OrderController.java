package com.ecommerce.ordertracker.controller;

import com.ecommerce.ordertracker.model.Order;
import com.ecommerce.ordertracker.dto.OrderFilterRequest;
import com.ecommerce.ordertracker.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend to access this API
public class OrderController {

    private final OrderService orderService;

    // ✅ GET paginated orders with optional status
    @GetMapping
    public Page<Order> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String status
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getAllOrders(status, pageable);
    }

    // ✅ POST: Create a new order (for testing from Postman or UI)
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }


    // ✅ POST: Filter with multiple fields
    @PostMapping("/filter")
    public Page<Order> filterOrders(
            @RequestBody OrderFilterRequest filterRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        System.out.println("Filter received: " + filterRequest);
        Pageable pageable = PageRequest.of(page, size);
        return orderService.filterOrders(filterRequest, pageable);
    }
}
