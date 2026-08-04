package com.company.coursemanagement.Main;

import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.infrastructure.repository.InMemoryStudentRepository;
import com.company.coursemanagement.infrastructure.repository.InMemoryCourseRepository;
import com.company.coursemanagement.infrastructure.repository.InMemoryEnrollmentRepository;
import com.company.coursemanagement.presentation.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        InMemoryStudentRepository studentRepo = new InMemoryStudentRepository();
        InMemoryCourseRepository courseRepo = new InMemoryCourseRepository();
        InMemoryEnrollmentRepository enrollmentRepo = new InMemoryEnrollmentRepository();

        StudentService studentService = new StudentService(studentRepo);
        CourseService courseService = new CourseService(courseRepo);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepo, studentRepo, courseRepo);

        MenuPrincipal menu = new MenuPrincipal(studentService, courseService, enrollmentService);
        menu.run();
    }
}