package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.response.resources.CreateCourseDTO;

import java.util.List;

public interface CourseServiceInterface {
    CreateCourseDTO createCourse(
            String code,
            String name,
            String description,
            Integer maxCapacity
    );

    CreateCourseDTO findById(Long id);

    List<CreateCourseDTO> findAll();

    CreateCourseDTO updateCourse(
            Long id,
            String code,
            String name,
            String description,
            Integer maxCapacity
    );

    void deleteCourse(Long id);
}
