package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryEnrollmentRepository implements EnrollmentRepository {

    private final List<Enrollment> store = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Enrollment save(Enrollment enrollment) {
        if (enrollment.getId() == null) {
            enrollment.setId(idGenerator.getAndIncrement());
        }
        if (enrollment.getEnrollmentDate() == null) {
            enrollment.setEnrollmentDate(LocalDate.now());
        }
        store.removeIf(e -> e.getId().equals(enrollment.getId()));
        store.add(enrollment);
        return enrollment;
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return store.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Enrollment> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        if (findById(enrollment.getId()).isEmpty()) {
            throw new EnrollmentNotFoundException(enrollment.getId());
        }
        store.removeIf(e -> e.getId().equals(enrollment.getId()));
        store.add(enrollment);
        return enrollment;
    }

    @Override
    public void deleteById(Long id) {
        store.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        return store.stream().filter(e -> e.getStudentId().equals(studentId)).collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findByCourseId(Long courseId) {
        return store.stream().filter(e -> e.getCourseId().equals(courseId)).collect(Collectors.toList());
    }
}