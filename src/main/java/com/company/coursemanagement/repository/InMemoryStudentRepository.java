package com.company.coursemanagement.repository;

import com.company.coursemanagement.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryStudentRepository extends JpaRepository<Student, Long> {
}