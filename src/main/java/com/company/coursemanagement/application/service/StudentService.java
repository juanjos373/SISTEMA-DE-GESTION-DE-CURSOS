package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.application.service.impl.StudentServiceInterface;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService implements StudentServiceInterface {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate
    ) {
        try {
            Student student = new Student();

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setBirthDate(birthDate);

            Student saved = studentRepository.save(student);

            return toDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el estudiante: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentDTO findById(Long id) {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException(id));

            return toDTO(student);
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el estudiante: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudentDTO> findAll() {
        try {
            return studentRepository.findAll()
                    .stream()
                    .map(this::toDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los estudiantes: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentDTO updateStudent(
            Long id,
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate
    ) {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException(id));

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setBirthDate(birthDate);

            Student updated = studentRepository.save(student);

            return toDTO(updated);
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el estudiante: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        try {
            studentRepository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException(id));

            studentRepository.deleteById(id);
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el estudiante: " + e.getMessage(), e);
        }
    }

    private StudentDTO toDTO(Student student) {

        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setBirthDate(student.getBirthDate());

        return dto;
    }
}
