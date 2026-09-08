package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.StudentDTO;

import java.time.LocalDate;
import java.util.List;

public interface StudentServiceInterface {
    StudentDTO createStudent(
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate
    );

    StudentDTO findById(Long id);

    List<StudentDTO> findAll();

    StudentDTO updateStudent(
            Long id,
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate
    );

    void deleteStudent(Long id);
}
