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
@Table(name = "student_payment")
public class StudentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false) // Many student bills can refer to one student
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // Foreign key to Student

    @Column(name = "description")
    private String description;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @ManyToOne(optional = false) // One bill can be mapped to only one student
    @JoinColumn(name = "bill_id", nullable = false)
    private Bills billId; // Foreign key to Bills
}
