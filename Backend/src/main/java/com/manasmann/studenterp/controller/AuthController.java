package com.manasmann.studenterp.controller;

import com.manasmann.studenterp.dto.LoginRequest;
import com.manasmann.studenterp.dto.LoginResponse;
import com.manasmann.studenterp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/student")
    public ResponseEntity<LoginResponse> studentLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.studentLogin(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.adminLogin(loginRequest);
        return ResponseEntity.ok(response);
    }
}
