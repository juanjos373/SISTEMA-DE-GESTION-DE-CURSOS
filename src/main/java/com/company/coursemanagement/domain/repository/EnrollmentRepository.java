package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Enrollment;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findById(Long id);
    List<Enrollment> findAll();
    Enrollment update(Enrollment enrollment);
    void deleteById(Long id);
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
}