package com.manasmann.studenterp.controller;

import com.manasmann.studenterp.dto.AdminRequest;
import com.manasmann.studenterp.entity.Admin;
import com.manasmann.studenterp.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody @Valid AdminRequest adminRequest) {
        Admin admin = adminService.createAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }
}
