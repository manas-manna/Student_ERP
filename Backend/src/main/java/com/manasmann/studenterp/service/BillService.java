package com.manasmann.studenterp.service;

import com.manasmann.studenterp.dto.BillRequest;
import com.manasmann.studenterp.dto.BillResponse;
import com.manasmann.studenterp.entity.Bills;
import com.manasmann.studenterp.entity.Student;
import com.manasmann.studenterp.entity.StudentBills;
import com.manasmann.studenterp.mapper.BillsMapper;
import com.manasmann.studenterp.mapper.StudentBillsMapper;
import com.manasmann.studenterp.repo.BillsRepository;
import com.manasmann.studenterp.repo.StudentBillsRepository;
import com.manasmann.studenterp.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillsRepository billsRepository;
    private final StudentBillsRepository studentBillsRepository;
    private final BillsMapper billsMapper;
    private final StudentBillsMapper studentBillsMapper;
    private final StudentRepository studentRepository; // Add StudentRepository to fetch the student

    public BillResponse createBill(BillRequest billRequest) {
        // Map BillRequest to Bills entity
        Bills bill = billsMapper.toBills(billRequest);
        Bills savedBill = billsRepository.save(bill);

        // Fetch the student entity using the studentId from BillRequest
        Student student = studentRepository.findById(billRequest.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Map student-bill association and save
        StudentBills studentBill = studentBillsMapper.toStudentBills(student, savedBill);
        studentBillsRepository.save(studentBill);

        return new BillResponse(savedBill.getId(), "Bill created and assigned to student successfully.");
    }
}