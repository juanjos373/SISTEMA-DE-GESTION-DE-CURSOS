package com.company.coursemanagement.repository;

import com.company.coursemanagement.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryCourseRepository extends JpaRepository<Course, Long> {
}