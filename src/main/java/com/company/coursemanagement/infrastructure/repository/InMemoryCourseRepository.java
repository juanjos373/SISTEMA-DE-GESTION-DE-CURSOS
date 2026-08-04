package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCourseRepository implements CourseRepository {

    private final List<Course> store = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Course save(Course course) {
        if (course.getId() == null) {
            course.setId(idGenerator.getAndIncrement());
        }
        store.removeIf(c -> c.getId().equals(course.getId()));
        store.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return store.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public Course update(Course course) {
        if (findById(course.getId()).isEmpty()) {
            throw new CourseNotFoundException(course.getId());
        }
        store.removeIf(c -> c.getId().equals(course.getId()));
        store.add(course);
        return course;
    }

    @Override
    public void deleteById(Long id) {
        store.removeIf(c -> c.getId().equals(id));
    }
}