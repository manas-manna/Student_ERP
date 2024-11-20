package com.manasmann.studenterp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bills")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "deadline")
    private LocalDate deadline;
}
