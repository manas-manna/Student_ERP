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

    @ManyToOne(optional = false) // Many student bills can refer to one student
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // Reference to Student entity

    @OneToOne(optional = false) // One bill can be mapped to only one student
    @JoinColumn(name = "bill_id", nullable = false)
    private Bills billId; // Reference to Bill entity
}
