package com.manasmann.studenterp.controller;

import com.manasmann.studenterp.dto.PaymentHistoryResponse;
import com.manasmann.studenterp.dto.PaymentRequest;
import com.manasmann.studenterp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> handlePayment(
            @RequestBody PaymentRequest paymentRequest) {
            paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok("Payment processed successfully");
    }

    @GetMapping
    public ResponseEntity<List<PaymentHistoryResponse>> getPaymentHistory(@RequestHeader("studentId") Long studentId) {
        List<PaymentHistoryResponse> paymentHistory = paymentService.getPaymentHistoryForStudent(studentId);
        return ResponseEntity.ok(paymentHistory);
    }
}
