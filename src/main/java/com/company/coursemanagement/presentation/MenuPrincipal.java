package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import java.util.Scanner;

public class MenuPrincipal {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final Scanner scanner;

    public MenuPrincipal(StudentService studentService, CourseService courseService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Sistema de Gestion de Cursos ===");
            System.out.println("1. Estudiantes");
            System.out.println("2. Cursos");
            System.out.println("3. Matriculas");
            System.out.println("0. Salir");

            int choice = readInt("Elija una opcion : ");
            switch (choice) {
                case 1:
                    new StudentMenu(studentService, scanner).show();
                    break;
                case 2:
                    new CourseMenu(courseService, scanner).show();
                    break;
                case 3:
                    new EnrollmentMenu(enrollmentService, scanner).show();
                    break;
                case 0:
                    running = false;
                    System.out.println("Un gusto servirle. Vuelva pronto");
                    break;
                default:
                    System.out.println("opcion invalida.");
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}