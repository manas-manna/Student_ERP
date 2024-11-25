package com.manasmann.studenterp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email",nullable = false, unique = true)
    private String email;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="password",nullable = false)
    private String password; // Will store the hashed password
}
