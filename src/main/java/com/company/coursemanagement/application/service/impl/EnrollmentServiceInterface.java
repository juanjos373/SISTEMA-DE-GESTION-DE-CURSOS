package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.request.CreateEnrollmentRequest;
import com.company.coursemanagement.application.dto.response.resources.CreateEnrollmentDTO;

import java.util.List;

public interface EnrollmentServiceInterface {
    CreateEnrollmentDTO createEnrollment(CreateEnrollmentRequest createEnrollmentRequest);

    CreateEnrollmentDTO findById(Long id);

    List<CreateEnrollmentDTO> findAll();

    CreateEnrollmentDTO cancelEnrollment(Long id);

    void deleteEnrollment(Long id);
}
