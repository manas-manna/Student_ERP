package com.manasmann.studenterp.controller;

import com.manasmann.studenterp.dto.PaymentRequest;
import com.manasmann.studenterp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> handlePayment(
            @RequestBody PaymentRequest paymentRequest) {
//        try {
            paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok("Payment processed successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing payment: " + e.getMessage());
//        }
    }
}
