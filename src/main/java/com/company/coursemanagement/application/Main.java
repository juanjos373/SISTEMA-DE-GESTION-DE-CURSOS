package com.company.coursemanagement.application;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.presentation.CourseMenu;
import com.company.coursemanagement.presentation.EnrollmentMenu;
import com.company.coursemanagement.presentation.StudentMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main(
            StudentService studentService,
            CourseService courseService,
            EnrollmentService enrollmentService
    ) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);

        StudentMenu studentMenu = new StudentMenu(studentService, scanner);
        CourseMenu courseMenu = new CourseMenu(courseService, scanner);
        EnrollmentMenu enrollmentMenu = new EnrollmentMenu(
                enrollmentService,
                scanner
        );

        boolean salir = false;

        while (!salir) {

            System.out.println();
            System.out.println("=== GESTION DE CURSOS ===");
            System.out.println("1. Estudiantes");
            System.out.println("2. Cursos");
            System.out.println("3. Inscripciones");
            System.out.println("0. Salir");

            int opcion = leerEntero(
                    scanner,
                    "Seleccione una opcion: "
            );

            switch (opcion) {
                case 1 -> studentMenu.mostrar();
                case 2 -> courseMenu.mostrar();
                case 3 -> enrollmentMenu.mostrar();
                case 0 -> salir = true;
                default -> System.out.println("Opcion no valida");
            }
        }

        System.out.println("Hasta pronto");

        scanner.close();
    }

    private int leerEntero(
            Scanner scanner,
            String mensaje
    ) {
        System.out.print(mensaje);

        while (true) {

            String linea = scanner.nextLine().trim();

            if (linea.isEmpty()) {
                continue;
            }

            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Ingrese un numero valido"
                );
                System.out.print(mensaje);
            }
        }
    }
}