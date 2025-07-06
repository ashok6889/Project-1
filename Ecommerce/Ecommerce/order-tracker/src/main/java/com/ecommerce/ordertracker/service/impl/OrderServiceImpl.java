package com.ecommerce.ordertracker.service.impl;

import com.ecommerce.ordertracker.model.Order;
import com.ecommerce.ordertracker.model.OrderStatus;
import com.ecommerce.ordertracker.repository.OrderRepository;
import com.ecommerce.ordertracker.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.ecommerce.ordertracker.dto.OrderFilterRequest;
import com.ecommerce.ordertracker.specification.OrderSpecification;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Page<Order> getAllOrders(String status, Pageable pageable) {
        if (status == null || status.isEmpty() || status.equalsIgnoreCase("ALL")) {
            return orderRepository.findAll(pageable);
        } else {
            OrderStatus parsedStatus = OrderStatus.valueOf(status.toUpperCase());
            return orderRepository.findAllByStatus(parsedStatus, pageable);
        }
    }
    @Override
    public Page<Order> filterOrders(OrderFilterRequest filterRequest, Pageable pageable) {
        return orderRepository.findAll(OrderSpecification.getFilteredOrders(filterRequest), pageable);
    }

    @Override
    public void saveSampleOrders() {
        if (orderRepository.count() == 0) {
            List<Order> orders = Arrays.asList(
                    Order.builder().orderId("ORD001").customer("Alice").orderItem("Mobile").deliveryDate(LocalDate.now().plusDays(2)).deliveryPricing(150.0).status(OrderStatus.COMPLETED).build(),
                    Order.builder().orderId("ORD002").customer("Bob").orderItem("Laptop").deliveryDate(LocalDate.now().plusDays(4)).deliveryPricing(350.0).status(OrderStatus.CONTINUING).build(),
                    Order.builder().orderId("ORD003").customer("Charlie").orderItem("Keyboard").deliveryDate(LocalDate.now().plusDays(1)).deliveryPricing(50.0).status(OrderStatus.RESTITUTE).build(),
                    Order.builder().orderId("ORD004").customer("David").orderItem("Monitor").deliveryDate(LocalDate.now().plusDays(3)).deliveryPricing(200.0).status(OrderStatus.CANCELED).build()
            );
            orderRepository.saveAll(orders);
        }
    }
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

}
