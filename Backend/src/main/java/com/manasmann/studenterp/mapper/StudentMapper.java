package com.manasmann.studenterp.mapper;

import com.manasmann.studenterp.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(Student student, String hashedPassword) {
        return Student.builder()
                .rollNumber(student.getRollNumber())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .password(hashedPassword) // Use hashed password
                .photographPath(student.getPhotographPath()) // Include photograph path
                .cgpa(student.getCgpa()) // Include CGPA
                .totalCredits(student.getTotalCredits()) // Include total credits
                .graduationYear(student.getGraduationYear()) // Include graduation year
                .specialisationId(student.getSpecialisationId())
                .domainId(student.getDomainId())
                .placementId(student.getPlacementId()) // Don't forget placementId
                .build();
    }
}
