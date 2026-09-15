package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.request.CreateStudentRequest;
import com.company.coursemanagement.application.dto.response.resources.CreateStudentDTO;

import java.util.List;

public interface StudentServiceInterface {
    CreateStudentDTO createStudent(CreateStudentRequest createStudentRequest);

    CreateStudentDTO findById(Long id);

    List<CreateStudentDTO> findAll();

    CreateStudentDTO updateStudent(
            Long id,
            String firstName,
            String lastName,
            String email,
            java.time.LocalDate birthDate
    );

    void deleteStudent(Long id);
}
