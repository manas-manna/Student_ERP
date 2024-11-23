package com.manasmann.studenterp.dto;

public record PaymentRequest(
        Long studentId,
        Long billId,
       Double amount ,    // Amount paid
       boolean useCredit
) {
}
