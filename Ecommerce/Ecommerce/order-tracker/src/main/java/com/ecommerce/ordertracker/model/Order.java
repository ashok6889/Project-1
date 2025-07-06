package com.ecommerce.ordertracker.model;
import com.ecommerce.ordertracker.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String customer;
    private String orderItem;
    private LocalDate deliveryDate;
    private Double deliveryPricing;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
