package com.manasmann.studenterp.service;

import com.manasmann.studenterp.dto.PaymentRequest;
import com.manasmann.studenterp.entity.Bills;
import com.manasmann.studenterp.entity.CreditBalance;
import com.manasmann.studenterp.entity.Student;
import com.manasmann.studenterp.entity.StudentPayment;
import com.manasmann.studenterp.repo.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final StudentPaymentRepository studentPaymentRepository;
    private final CreditBalanceRepository creditBalanceRepository;
    private final StudentBillsRepository studentBillsRepository;
    private final StudentRepository studentRepository;
    private final BillsRepository billsRepository;


    public void processPayment(PaymentRequest paymentRequest) {
        Long studentId = paymentRequest.studentId();
        Long billId = paymentRequest.billId();

        // Fetch the student and bill
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        Bills bill = billsRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + billId));

        // Fetch or create a new credit balance
        CreditBalance creditBalance = creditBalanceRepository.findByStudent_StudentId(studentId)
                .orElseGet(() -> {
                    CreditBalance newBalance = new CreditBalance(student, 0.0);
                    System.out.println("Printing new balance"+newBalance);
                    creditBalanceRepository.save(newBalance);
                    return newBalance;
                });

        double remainingAmount = paymentRequest.amount();
        boolean useCredit = paymentRequest.useCredit();

        // Deduct credit balance if applicable
        if (useCredit && creditBalance.getBalance() > 0) {
            if (creditBalance.getBalance() >= remainingAmount) {
                // Credit fully covers the payment
                creditBalance.setBalance(creditBalance.getBalance() - remainingAmount);
                remainingAmount = 0.0;
            } else {
                // Partial credit usage
                remainingAmount -= creditBalance.getBalance();
                creditBalance.setBalance(0.0);
            }
            creditBalanceRepository.save(creditBalance); // Save after updating
        }

        // Save the payment record
        StudentPayment payment = new StudentPayment();
        payment.setStudent(student);
        payment.setBillId(bill);
        payment.setDescription("Payment for bill ID " + billId);
        payment.setAmount(paymentRequest.amount());
        payment.setPaymentDate(LocalDate.now());
        studentPaymentRepository.save(payment);

        // Handle overpayment
        if (remainingAmount < 0) {
            double overpayment = Math.abs(remainingAmount);
            creditBalance.setBalance(creditBalance.getBalance() + overpayment); // Add to credit balance
            creditBalanceRepository.save(creditBalance); // Always save updated balance
        }

        // Always save credit balance to ensure persistence
        creditBalanceRepository.save(creditBalance);
    }
}