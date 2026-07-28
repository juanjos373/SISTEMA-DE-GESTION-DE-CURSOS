package com.company.coursemanagement.domain.exception;

public class EnrollmentNotFoundException extends BusinessException {

    public EnrollmentNotFoundException(Long id) {
        super("Matricula no encontrada: " + id);
    }
}