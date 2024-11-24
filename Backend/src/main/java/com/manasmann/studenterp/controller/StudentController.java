package com.manasmann.studenterp.controller;

import com.manasmann.studenterp.dto.AllBillResponse;
import com.manasmann.studenterp.dto.BillResponse;
import com.manasmann.studenterp.dto.GetAllStudentResponse;
import com.manasmann.studenterp.entity.Student;
import com.manasmann.studenterp.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }


    @GetMapping
    public ResponseEntity<List<GetAllStudentResponse>> getAllStudents() {
        List<GetAllStudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/bills/{studentId}")
    public ResponseEntity<List<AllBillResponse>> getBillsByStudentId(@PathVariable Long studentId,@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        List<AllBillResponse> bills = studentService.getBillsByStudentId(studentId);
        return ResponseEntity.ok(bills);
    }
}