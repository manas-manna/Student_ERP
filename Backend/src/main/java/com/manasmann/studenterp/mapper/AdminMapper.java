package com.manasmann.studenterp.mapper;

import com.manasmann.studenterp.dto.AdminRequest;
import com.manasmann.studenterp.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminMapper {
    public Admin toAdmin(AdminRequest adminRequest, String hashedPassword) {
        return Admin.builder()
                .email(adminRequest.email())
                .firstName(adminRequest.firstName())
                .lastName(adminRequest.lastName())
                .password(hashedPassword) // Use hashed password
                .build();
    }
}
