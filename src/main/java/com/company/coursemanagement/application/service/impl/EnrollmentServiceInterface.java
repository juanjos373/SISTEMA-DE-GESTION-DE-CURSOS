package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentServiceInterface {
    EnrollmentDTO createEnrollment(Long studentId, Long courseId);

    EnrollmentDTO findById(Long id);

    List<EnrollmentDTO> findAll();

    EnrollmentDTO cancelEnrollment(Long id);

    void deleteEnrollment(Long id);
}
