package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryStudentRepository implements StudentRepository {

    private final List<Student> store = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idGenerator.getAndIncrement());
        }
        store.removeIf(s -> s.getId().equals(student.getId()));
        store.add(student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return store.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public Student update(Student student) {
        if (findById(student.getId()).isEmpty()) {
            throw new StudentNotFoundException(student.getId());
        }
        store.removeIf(s -> s.getId().equals(student.getId()));
        store.add(student);
        return student;
    }

    @Override
    public void deleteById(Long id) {
        store.removeIf(s -> s.getId().equals(id));
    }
}