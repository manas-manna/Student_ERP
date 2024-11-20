package com.manasmann.studenterp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_bills")
public class StudentBills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId; // Foreign key to Student

    @Column(name = "bill_id", nullable = false)
    private Long billId; // Foreign key to Bills
}
