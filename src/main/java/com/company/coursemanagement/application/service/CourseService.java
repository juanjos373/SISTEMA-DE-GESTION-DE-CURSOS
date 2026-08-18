package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDTO createCourse(
            String code,
            String name,
            String description,
            Integer maxCapacity
    ) {
        Course course = new Course();

        course.setCode(code);
        course.setName(name);
        course.setDescription(description);
        course.setMaxCapacity(maxCapacity);

        Course saved = courseRepository.save(course);

        return toDTO(saved);
    }

    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return toDTO(course);
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CourseDTO updateCourse(
            Long id,
            String code,
            String name,
            String description,
            Integer maxCapacity
    ) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        course.setCode(code);
        course.setName(name);
        course.setDescription(description);
        course.setMaxCapacity(maxCapacity);

        Course updated = courseRepository.save(course);

        return toDTO(updated);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        courseRepository.delete(course);
    }

    private CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();

        dto.setId(course.getId());
        dto.setCode(course.getCode());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setMaxCapacity(course.getMaxCapacity());

        return dto;
    }
}