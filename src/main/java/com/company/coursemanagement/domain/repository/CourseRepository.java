package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAll();
    Course update(Course course);
    void deleteById(Long id);
}