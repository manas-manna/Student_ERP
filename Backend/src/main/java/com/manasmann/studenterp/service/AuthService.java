package com.manasmann.studenterp.service;

import com.manasmann.studenterp.dto.LoginRequest;
import com.manasmann.studenterp.dto.LoginResponse;
import com.manasmann.studenterp.entity.Student;
import com.manasmann.studenterp.entity.Admin;
import com.manasmann.studenterp.helper.JWTHelper;
import com.manasmann.studenterp.repo.StudentRepository;
import com.manasmann.studenterp.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTHelper JWTHelper;

    public LoginResponse studentLogin(LoginRequest loginRequest) {
        Student student = studentRepository.findByRollNumber(loginRequest.identifier())
                .orElseThrow(() -> new RuntimeException("Invalid roll number or password"));

        if (!passwordEncoder.matches(loginRequest.password(), student.getPassword())) {
            throw new RuntimeException("Invalid roll number or password");
        }

        String token = JWTHelper.generateToken(student.getRollNumber());

        return new LoginResponse(student.getStudentId(),student.getFirstName(), student.getRollNumber(), "Login successful", token);
    }


    public LoginResponse adminLogin(LoginRequest loginRequest) {
        Admin admin = adminRepository.findByEmail(loginRequest.identifier())
                .orElseThrow(() -> new RuntimeException("Invalid email or password")
                );

        if (!passwordEncoder.matches(loginRequest.password(), admin.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = JWTHelper.generateToken(admin.getEmail());

        return new LoginResponse(admin.getId(),admin.getFirstName(), admin.getEmail(), "Login successful", token);
    }

}
