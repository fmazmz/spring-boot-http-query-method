package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final List<Order> orders = List.of(
            new Order("John Smith", "OPEN", "120.00", "2026-08-01", List.of("vip"), "Stockholm", "SE"),
            new Order("Jenny Smith", "PAID", "45.50", "2026-09-02", List.of("vip", "gift"), "Gothenburg", "SE"),
            new Order("Alan Turing", "OPEN", "310.00", "2026-09-10", List.of("urgent"), "London", "GB"),
            new Order("Test Testing", "CANCELLED", "19.00", "2026-07-15", List.of(), "New York", "US"),
            new Order("Testing Testsson", "PAID", "88.00", "2026-09-12", List.of("gift"), "Amsterdam", "NL")
    );

    @GetMapping("/orders")
    public List<Order> list() {
        return orders;
    }

    @RequestMapping(method = RequestMethod.QUERY, path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> query(@RequestBody(required = false) OrderQuery query) {
        if (query == null) {
            return orders;
        }
        return orders.stream().filter(query::matches).toList();
    }
}
