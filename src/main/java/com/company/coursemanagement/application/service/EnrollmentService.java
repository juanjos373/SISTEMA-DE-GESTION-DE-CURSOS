package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.EnrollmentDTO;
import com.company.coursemanagement.application.service.impl.EnrollmentServiceInterface;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.repository.InMemoryEnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService implements EnrollmentServiceInterface {

    private final InMemoryEnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            InMemoryEnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public EnrollmentDTO createEnrollment(Long studentId, Long courseId) {
        try {
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
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (CourseNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la matricula: " + e.getMessage(), e);
        }
    }

    @Override
    public EnrollmentDTO findById(Long id) {
        try {
            Enrollment enrollment = enrollmentRepository.findById(id)
                    .orElseThrow(() -> new EnrollmentNotFoundException(id));

            return toDTO(enrollment);
        } catch (EnrollmentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la matricula: " + e.getMessage(), e);
        }
    }

    @Override
    public List<EnrollmentDTO> findAll() {
        try {
            return enrollmentRepository.findAll()
                    .stream()
                    .map(this::toDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las matriculas: " + e.getMessage(), e);
        }
    }

    @Override
    public EnrollmentDTO cancelEnrollment(Long id) {
        try {
            Enrollment enrollment = enrollmentRepository.findById(id)
                    .orElseThrow(() -> new EnrollmentNotFoundException(id));

            if (enrollment.getStatus() == EnrollmentStatus.CANCELLED) {
                throw new BusinessException(
                        "La matricula ya esta cancelada: " + id
                );
            }

            enrollment.setStatus(EnrollmentStatus.CANCELLED);

            Enrollment updated = enrollmentRepository.save(enrollment);

            return toDTO(updated);
        } catch (EnrollmentNotFoundException e) {
            throw e;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al cancelar la matricula: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteEnrollment(Long id) {
        try {
            enrollmentRepository.findById(id)
                    .orElseThrow(() -> new EnrollmentNotFoundException(id));

            enrollmentRepository.deleteById(id);
        } catch (EnrollmentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la matricula: " + e.getMessage(), e);
        }
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
