package com.manasmann.studenterp.dto;


public record LoginRequest(
        String identifier, // For admin: email; For student: rollNo
        String password
) {}
