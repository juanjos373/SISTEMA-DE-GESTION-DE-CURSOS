package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.application.service.impl.CourseServiceInterface;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseServiceInterface {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO createCourse(
            String code,
            String name,
            String description,
            Integer maxCapacity
    ) {
        try {
            Course course = new Course();

            course.setCode(code);
            course.setName(name);
            course.setDescription(description);
            course.setMaxCapacity(maxCapacity);

            Course saved = courseRepository.save(course);

            return toDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el curso: " + e.getMessage(), e);
        }
    }

    @Override
    public CourseDTO findById(Long id) {
        try {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new CourseNotFoundException(id));

            return toDTO(course);
        } catch (CourseNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el curso: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CourseDTO> findAll() {
        try {
            return courseRepository.findAll()
                    .stream()
                    .map(this::toDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los cursos: " + e.getMessage(), e);
        }
    }

    @Override
    public CourseDTO updateCourse(
            Long id,
            String code,
            String name,
            String description,
            Integer maxCapacity
    ) {
        try {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new CourseNotFoundException(id));

            course.setCode(code);
            course.setName(name);
            course.setDescription(description);
            course.setMaxCapacity(maxCapacity);

            Course updated = courseRepository.save(course);

            return toDTO(updated);
        } catch (CourseNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el curso: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCourse(Long id) {
        try {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new CourseNotFoundException(id));

            courseRepository.delete(course);
        } catch (CourseNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el curso: " + e.getMessage(), e);
        }
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
