package com.manasmann.studenterp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminRequest(
        @NotNull
        @Email String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password
) {}
