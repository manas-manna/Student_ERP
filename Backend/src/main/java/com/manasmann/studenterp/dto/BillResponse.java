package com.manasmann.studenterp.dto;

public record BillResponse(
        Long billId, // The ID of the created bill
        String message // Success or informational message
) {}