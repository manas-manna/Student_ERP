package com.manasmann.studenterp.repo;

import com.manasmann.studenterp.entity.Bills;
import com.manasmann.studenterp.entity.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {

    List<StudentPayment> findByBillId(Bills bills);
    List<StudentPayment> findByStudent_StudentId(Long studentId);
}