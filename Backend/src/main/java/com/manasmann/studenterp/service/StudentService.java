package com.manasmann.studenterp.service;

import com.manasmann.studenterp.dto.AllBillResponse;
import com.manasmann.studenterp.dto.BillResponse;
import com.manasmann.studenterp.dto.GetAllStudentResponse;
import com.manasmann.studenterp.entity.*;
import com.manasmann.studenterp.exception.UnauthorisedAccessException;
import com.manasmann.studenterp.mapper.BillsMapper;
import com.manasmann.studenterp.mapper.StudentMapper;
import com.manasmann.studenterp.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final BillsMapper billsMapper;
    private final PasswordEncoder passwordEncoder;
    private final StudentBillsRepository studentBillsRepository;
    private final BillsRepository billsRepository;
    private final CreditBalanceRepository creditBalanceRepository;
    private final StudentPaymentRepository studentPaymentRepository;

    @Autowired
    private HttpServletRequest request;

    public Student createStudent(Student student) {
        String hashedPassword = passwordEncoder.encode(student.getPassword());
        Student mappedStudent = studentMapper.toStudent(student, hashedPassword);
        return studentRepository.save(mappedStudent);
    }


    public List<GetAllStudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> new GetAllStudentResponse(
                        student.getStudentId(),
                        student.getRollNumber(),
                        student.getFirstName() + " " + student.getLastName()))
                .sorted(Comparator.comparing(GetAllStudentResponse::rollNumber)) // Sort by rollNumber
                .toList();
    }

    public List<AllBillResponse> getBillsByStudentId(Long studentId) {
        // Fetch all bill IDs associated with the student
        List<StudentBills> studentBills = studentBillsRepository.findByStudent_StudentId(studentId);


        String AuthenticatedId = (String)request.getAttribute("AuthenticatedIdentifier");
        if(!AuthenticatedId.equals(String.valueOf(studentId))){
            throw new UnauthorisedAccessException("You can only process your payments.");
        }


        // Extract the associated bills from the StudentBills entities
        List<Bills> bills = studentBills.stream()
                .map(StudentBills::getBillId) // Fetch the Bills entity
                .toList();

        // Fetch the credit balance for the student
        Double creditBalance = creditBalanceRepository.findByStudent_StudentId(studentId)
                .map(CreditBalance::getBalance)  // Extract balance if present
                .orElse(0.0);




        // Iterate through the bills and check if they are fully paid
        List<AllBillResponse> allBillResponses = bills.stream()
                .filter(bill -> {
                    // Fetch all payments for the current bill and student
                    List<StudentPayment> payments = studentPaymentRepository.findByBillId(bill);

                    // Calculate the total payments for this bill
                    Double totalPaid = payments.stream()
                            .mapToDouble(StudentPayment::getAmount)
                            .sum();

                    return totalPaid < bill.getAmount(); 
                })
                .map(bill -> {
                    // Fetch all payments for the current bill
                    List<StudentPayment> payments = studentPaymentRepository.findByBillId(bill);

                    // Calculate the total payments for this bill
                    Double totalPaid = payments.stream()
                            .mapToDouble(StudentPayment::getAmount)
                            .sum();

                    // Calculate the current due
                    Double currentDue = bill.getAmount() - totalPaid;

                    // Map each bill to a response with current due
                    return billsMapper.toAllBillResponse(bill, creditBalance, currentDue);
                }) // Map each bill to a response
                .collect(Collectors.toList());

        return allBillResponses;
    }

}