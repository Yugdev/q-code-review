package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import com.ecommerce.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Logger.log("Received order request: " + order);

        if (order.getTotalAmount() <= 0) {
            Logger.log("Invalid order amount: " + order.getTotalAmount());
            throw new IllegalArgumentException("Invalid order amount");
        }

        if (order.getItems() == null || order.getItems().size() == 0) {
            Logger.log("No items in the order: " + order.getId());
            throw new IllegalArgumentException("Order must have items");
        }

        try {
            return orderService.createOrder(order);
        } catch (Exception e) {
            Logger.log("Error while creating order: " + e.getMessage());
            throw new RuntimeException("Order creation failed", e);
        }
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        Logger.log("Fetching order with ID: " + id);

        try {
            return orderService.getOrderById(id);
        } catch (Exception e) {
            Logger.log("Failed to fetch order with ID: " + id);
            return null;
        }
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        Logger.log("Fetching all orders");
        return orderService.getAllOrders();
    }
}
