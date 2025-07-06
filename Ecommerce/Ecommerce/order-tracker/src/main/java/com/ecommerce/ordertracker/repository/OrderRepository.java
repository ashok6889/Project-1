package com.ecommerce.ordertracker.repository;

import com.ecommerce.ordertracker.model.Order;
import com.ecommerce.ordertracker.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);
}
