package com.manasmann.studenterp.service;


import com.manasmann.studenterp.dto.AdminRequest;
import com.manasmann.studenterp.entity.Admin;
import com.manasmann.studenterp.mapper.AdminMapper;
import com.manasmann.studenterp.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public Admin createAdmin(AdminRequest adminRequest) {
        String hashedPassword = passwordEncoder.encode(adminRequest.password());
        Admin admin = adminMapper.toAdmin(adminRequest, hashedPassword);
        return adminRepository.save(admin);
    }
}