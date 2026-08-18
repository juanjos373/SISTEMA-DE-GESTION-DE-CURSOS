package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.application.dto.StudentDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    private final StudentService studentService;
    private final Scanner scanner;

    public StudentMenu(StudentService studentService, Scanner scanner) {
        this.studentService = studentService;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu Estudiantes ---");
            System.out.println("1. Crear");
            System.out.println("2. Encontrar por id");
            System.out.println("3. Listar todos");
            System.out.println("4. Actualizar");
            System.out.println("5. Borrar");
            System.out.println("0. Volver al menu");

            int choice = readInt("Elija una opcion: ");
            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findStudentById();
                    break;
                case 3:
                    listAllStudents();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private void createStudent() {
        String firstName = readString("Nombre: ");
        String lastName = readString("Apellido: ");
        String email = readString("Email: ");
        LocalDate birthDate = LocalDate.parse(readString("Fecha de nacimiento (YYYY-MM-DD): "));
        try {
            StudentDTO dto = studentService.createStudent(firstName, lastName, email, birthDate);
            System.out.println("Estudiante creado con id: " + dto.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findStudentById() {
        Long id = readLong("Digite el id del estudiante: ");
        try {
            StudentDTO dto = studentService.findById(id);
            System.out.println(dto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAllStudents() {
        List<StudentDTO> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("No hay estudiantes encontrados.");
        } else {
            students.forEach(s -> System.out.println(s.toString()));
        }
    }

    private void updateStudent() {
        Long id = readLong("Digite el id del estudiante a actualizar: ");
        String firstName = readString("Nombre: ");
        String lastName = readString("Apellido: ");
        String email = readString("Email: ");
        LocalDate birthDate = LocalDate.parse(readString("Fecha de nacimiento (YYYY-MM-DD): "));
        try {
            StudentDTO dto = studentService.updateStudent(id, firstName, lastName, email, birthDate);
            System.out.println("Estudiante actualizado: " + dto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        Long id = readLong("Digite el id del estudiante a eliminar: ");
        try {
            studentService.deleteStudent(id);
            System.out.println("Estudiante eliminado.");
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