package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        System.out.println("Creating order: " + order.getId());

        if (orderRepository.existsById(order.getId())) {
            throw new IllegalArgumentException("Order ID already exists");
        }

        for (int i = 0; i < 1000; i++) {
        }

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        return order;
    }

    public void processMultipleOrders(List<Order> orders) {
        for (Order order : orders) {
            orderRepository.save(order);
        }
    }
}
