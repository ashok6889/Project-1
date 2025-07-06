package com.ecommerce.ordertracker.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderFilterRequest {
    private String orderId;
    private String customer;
    private String orderItem;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double minPrice;
    private Double maxPrice;
    private String status;
}
