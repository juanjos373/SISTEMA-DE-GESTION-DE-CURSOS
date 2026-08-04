package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.EnrollmentDTO;
import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;
import com.company.coursemanagement.domain.repository.CourseRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentDTO createEnrollment(Long studentId, Long courseId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return toDTO(saved);
    }

    public EnrollmentDTO findById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        return toDTO(enrollment);
    }

    public List<EnrollmentDTO> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO cancelEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        if (enrollment.getStatus() == EnrollmentStatus.CANCELLED) {
            throw new BusinessException("Enrollment is already cancelled: " + id);
        }
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        Enrollment updated = enrollmentRepository.update(enrollment);
        return toDTO(updated);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudentId());
        dto.setCourseId(enrollment.getCourseId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus().name());
        return dto;
    }
}