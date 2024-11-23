package com.manasmann.studenterp.repo;

import com.manasmann.studenterp.dto.GetAllStudentResponse;
import com.manasmann.studenterp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollNumber(String rollNumber);

//    @Query("SELECT new GetAllStudentResponse(s.studentId, s.rollNumber) FROM Student s")s
//    List<GetAllStudentResponse> findAll();

}
