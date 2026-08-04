package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudent(String firstName, String lastName, String email, LocalDate birthDate) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBirthDate(birthDate);
        Student saved = studentRepository.save(student);
        return toDTO(saved);
    }

    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toDTO(student);
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO updateStudent(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBirthDate(birthDate);
        Student updated = studentRepository.update(student);
        return toDTO(updated);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
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