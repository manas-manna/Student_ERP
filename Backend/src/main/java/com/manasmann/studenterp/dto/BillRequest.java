package com.manasmann.studenterp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BillRequest(
        @NotBlank(message = "Description is required")
        @JsonProperty("description")
        String description,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        @JsonProperty("amount")
        Double amount,

        @NotNull(message = "Due date is required")
        LocalDate dueDate,

        @NotNull(message = "Deadline is required")
        LocalDate deadline,

        @NotNull(message = "Student ID is required")
        @Positive(message = "Student ID must be a positive number")
        Long studentId
) {}