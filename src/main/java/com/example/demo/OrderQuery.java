package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderQuery(
        String customer,
        List<String> statuses,
        BigDecimal minTotal,
        BigDecimal maxTotal,
        LocalDate from,
        LocalDate to,
        List<String> tags,
        Address shipping
) {
    public boolean matches(Order order) {
        if (customer != null && !order.customer().toLowerCase().contains(customer.toLowerCase())) {
            return false;
        }
        if (statuses != null && !statuses.isEmpty() && !statuses.contains(order.status())) {
            return false;
        }
        if (minTotal != null && order.total().compareTo(minTotal) < 0) {
            return false;
        }
        if (maxTotal != null && order.total().compareTo(maxTotal) > 0) {
            return false;
        }
        if (from != null && order.createdOn().isBefore(from)) {
            return false;
        }
        if (to != null && order.createdOn().isAfter(to)) {
            return false;
        }
        if (tags != null && !order.tags().containsAll(tags)) {
            return false;
        }
        if (shipping != null) {
            if (shipping.country() != null && !shipping.country().equalsIgnoreCase(order.shipping().country())) {
                return false;
            }
            if (shipping.city() != null && !shipping.city().equalsIgnoreCase(order.shipping().city())) {
                return false;
            }
        }
        return true;
    }
}
