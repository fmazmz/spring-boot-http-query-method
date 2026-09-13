package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record Order(
        UUID id,
        String customer,
        String status,
        BigDecimal total,
        LocalDate createdOn,
        List<String> tags,
        Address shipping
) {
    public Order(
            String customer,
            String status,
            String total,
            String createdOn,
            List<String> tags,
            String city,
            String country
    ) {
        this(
                UUID.randomUUID(),
                customer,
                status,
                new BigDecimal(total),
                LocalDate.parse(createdOn),
                tags,
                new Address(city, country)
        );
    }
}
