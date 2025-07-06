package com.ecommerce.ordertracker;

import com.ecommerce.ordertracker.model.Order;
import com.ecommerce.ordertracker.model.OrderStatus;
import com.ecommerce.ordertracker.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ecommerce.ordertracker.model.OrderStatus;


import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class OrderDataTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void insertSampleData() {
        List<Order> orders = List.of(
                new Order(null, "ORD010", "Kalyani", "LG Washing Machine", LocalDate.now().plusDays(3), 210.0, OrderStatus.CONTINUING),
                new Order(null, "ORD011", "Mahesh Babu", "AC", LocalDate.now().plusDays(4), 500.0, OrderStatus.COMPLETED),
                new Order(null, "ORD012", "Ravi Teja", "Sony Speaker", LocalDate.now().plusDays(2), 190.0, OrderStatus.RESTITUTE)
        );
        orderRepository.saveAll(orders);
    }
}
