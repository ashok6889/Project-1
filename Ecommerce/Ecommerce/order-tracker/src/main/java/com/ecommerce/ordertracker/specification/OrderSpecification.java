
package com.ecommerce.ordertracker.specification;

import com.ecommerce.ordertracker.model.Order;
import com.ecommerce.ordertracker.model.OrderStatus;
import com.ecommerce.ordertracker.dto.OrderFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {
    public static Specification<Order> getFilteredOrders(OrderFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getOrderId() != null) {
                predicates.add(cb.like(cb.lower(root.get("orderId")), "%" + filter.getOrderId().toLowerCase() + "%"));
            }

            if (filter.getCustomer() != null) {
                predicates.add(cb.like(cb.lower(root.get("customer")), "%" + filter.getCustomer().toLowerCase() + "%"));
            }

            if (filter.getOrderItem() != null) {
                predicates.add(cb.like(cb.lower(root.get("orderItem")), "%" + filter.getOrderItem().toLowerCase() + "%"));
            }

            if (filter.getStartDate() != null && filter.getEndDate() != null) {
                predicates.add(cb.between(root.get("deliveryDate"), filter.getStartDate(), filter.getEndDate()));
            }

     
            if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
                predicates.add(cb.between(root.get("deliveryPricing"), filter.getMinPrice(), filter.getMaxPrice()));
            }


            if (filter.getStatus() != null && !filter.getStatus().equalsIgnoreCase("ALL")) {
                predicates.add(cb.equal(root.get("status"), OrderStatus.valueOf(filter.getStatus().toUpperCase())));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}