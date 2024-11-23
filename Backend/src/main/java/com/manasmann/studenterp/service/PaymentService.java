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
        Double dueAmount = paymentRequest.totalDue();
        Double customAmount = paymentRequest.amount();
        Boolean isCreditSelected = paymentRequest.useCredit();
        Boolean isTotalSelected = paymentRequest.useTotal();

        // Fetch the student and bill
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        Bills bill = billsRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + billId));

        // Fetch or create a new credit balance
        CreditBalance creditBalance = creditBalanceRepository.findByStudent_StudentId(studentId)
                .orElseGet(() -> {
                    CreditBalance newBalance = new CreditBalance(student, 0.0);
                    creditBalanceRepository.save(newBalance);
                    return newBalance;
                });
        Double creditAmount = creditBalance.getBalance();


        // Save the payment record
        StudentPayment payment = new StudentPayment();
        payment.setStudent(student);
        payment.setBillId(bill);
        payment.setDescription("Payment for bill ID " + billId);
        if(isCreditSelected) {
            payment.setAmount(paymentRequest.amount()+creditAmount);
        }else{
            payment.setAmount(paymentRequest.amount());
        }
        payment.setPaymentDate(LocalDate.now());
        studentPaymentRepository.save(payment);



        //updating the credit balance as per all the conditions
        if(isTotalSelected) {
            if(isCreditSelected){
                if(creditAmount>dueAmount){
                    creditAmount = creditAmount - dueAmount;
                    dueAmount = 0.0;
                }else{
                    dueAmount = dueAmount - creditAmount;
                    creditAmount = 0.0;
                }
            }
        }else{
            if(isCreditSelected){
                if(creditAmount>dueAmount){
                    creditAmount = creditAmount - dueAmount;
                    dueAmount = 0.0;
                    creditAmount = creditAmount + customAmount;
                }else{
                    dueAmount = dueAmount - creditAmount;
                    creditAmount = 0.0;
                    if(dueAmount<customAmount){
                        creditAmount = customAmount - dueAmount;
                    }
                }
            }else{
                if(dueAmount<customAmount){
                    customAmount = customAmount - dueAmount;
                    dueAmount = 0.0;
                    creditAmount = customAmount;
                }else{
                    dueAmount = dueAmount - customAmount;
                    customAmount = 0.0;
                }
            }
        }

        creditBalance.setBalance(creditAmount);
        creditBalanceRepository.save(creditBalance);

    }
}