package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.CourseDTO;

import java.util.List;

public interface CourseServiceInterface {
    CourseDTO createCourse(
            String code,
            String name,
            String description,
            Integer maxCapacity
    );

    CourseDTO findById(Long id);

    List<CourseDTO> findAll();

    CourseDTO updateCourse(
            Long id,
            String code,
            String name,
            String description,
            Integer maxCapacity
    );

    void deleteCourse(Long id);
}
