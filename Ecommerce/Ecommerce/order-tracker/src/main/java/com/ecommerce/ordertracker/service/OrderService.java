package com.ecommerce.ordertracker.service;

import com.ecommerce.ordertracker.model.Order;
import com.ecommerce.ordertracker.dto.OrderFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> getAllOrders(String status, Pageable pageable);
    Page<Order> filterOrders(OrderFilterRequest filterRequest, Pageable pageable);
    void saveSampleOrders();
    Order createOrder(Order order); // ✅ Make sure this line exists
}
