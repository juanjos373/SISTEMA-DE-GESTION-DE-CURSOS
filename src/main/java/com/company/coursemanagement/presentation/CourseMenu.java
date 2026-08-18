package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.dto.CourseDTO;
import java.util.List;
import java.util.Scanner;

public class CourseMenu {

    private final CourseService courseService;
    private final Scanner scanner;

    public CourseMenu(CourseService courseService, Scanner scanner) {
        this.courseService = courseService;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu Curso ---");
            System.out.println("1. Crear");
            System.out.println("2. Encontrar por Id");
            System.out.println("3. Listar todos");
            System.out.println("4. Actualizar");
            System.out.println("5. Borrar");
            System.out.println("0. Volver al menu");

            int choice = readInt("Elija una opcion: ");
            switch (choice) {
                case 1:
                    createCourse();
                    break;
                case 2:
                    findCourseById();
                    break;
                case 3:
                    listAllCourses();
                    break;
                case 4:
                    updateCourse();
                    break;
                case 5:
                    deleteCourse();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private void createCourse() {
        String code = readString("Codigo del curso: ");
        String name = readString("Nombre del curso: ");
        String description = readString("Descripcion: ");
        Integer maxCapacity = readInt("Capacidad maxima: ");
        try {
            CourseDTO dto = courseService.createCourse(code, name, description, maxCapacity);
            System.out.println("Curso creado con id: " + dto.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findCourseById() {
        Long id = readLong("Curso id: ");
        try {
            CourseDTO dto = courseService.findById(id);
            System.out.println(dto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAllCourses() {
        List<CourseDTO> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("No hay cursos encontrados.");
        } else {
            courses.forEach(c -> System.out.println(c.toString()));
        }
    }

    private void updateCourse() {
        Long id = readLong("Digite el id del curso a actualizar: ");
        String code = readString("Codigo del curso: ");
        String name = readString("Nombre del curso: ");
        String description = readString("Descripcion: ");
        Integer maxCapacity = readInt("Capacidad maxima: ");
        try {
            CourseDTO dto = courseService.updateCourse(id, code, name, description, maxCapacity);
            System.out.println("Curso actualizado: " + dto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteCourse() {
        Long id = readLong("Digite el id del curso a eliminar: ");
        try {
            courseService.deleteCourse(id);
            System.out.println("Curso eliminado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
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

    public void mostrar() {

    }
}