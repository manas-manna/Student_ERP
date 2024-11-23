package com.manasmann.studenterp.mapper;

import com.manasmann.studenterp.entity.Bills;
import com.manasmann.studenterp.entity.Student;
import com.manasmann.studenterp.entity.StudentBills;
import org.springframework.stereotype.Service;

@Service
public class StudentBillsMapper {

    public StudentBills toStudentBills(Student student, Bills bill) {
        return StudentBills.builder()
                .student(student)
                .billId(bill)
                .build();
    }
}