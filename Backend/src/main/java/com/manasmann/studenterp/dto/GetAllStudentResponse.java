package com.manasmann.studenterp.dto;

public record GetAllStudentResponse(
        Long studentId,
        String rollNumber,
        String name
) {
}
