package com.company.coursemanagement.domain.exception;

public class StudentNotFoundException extends BusinessException {

    public StudentNotFoundException(Long id) {
        super("Estudiante no encontrado: " + id);
    }
}