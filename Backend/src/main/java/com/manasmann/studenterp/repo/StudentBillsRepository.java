package com.manasmann.studenterp.repo;

import com.manasmann.studenterp.entity.StudentBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentBillsRepository extends JpaRepository<StudentBills, Long> {

    List<StudentBills> findByStudent_StudentId(Long studentId);

}