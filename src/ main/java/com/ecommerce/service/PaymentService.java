package com.ecommerce.service;

import com.ecommerce.model.Payment;
import com.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        if (payment == null || payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }

        try {
            return paymentRepository.save(payment);
        } catch (Exception e) {
            throw new RuntimeException("Payment processing failed", e);
        }
    }
}
