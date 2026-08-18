package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.application.dto.EnrollmentDTO;
import java.util.List;
import java.util.Scanner;

public class EnrollmentMenu {

    private final EnrollmentService enrollmentService;
    private final Scanner scanner;

    public EnrollmentMenu(EnrollmentService enrollmentService, Scanner scanner) {
        this.enrollmentService = enrollmentService;
        this.scanner = scanner;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu Matricula ---");
            System.out.println("1. Crear matricula");
            System.out.println("2. Encontrar por id");
            System.out.println("3. Listar todas");
            System.out.println("4. Cancelar matricula");
            System.out.println("5. Borrar matricula");
            System.out.println("0. Volver al menu");

            int choice = readInt("Elija una opcion: ");
            switch (choice) {
                case 1:
                    createEnrollment();
                    break;
                case 2:
                    findEnrollmentById();
                    break;
                case 3:
                    listAllEnrollments();
                    break;
                case 4:
                    cancelEnrollment();
                    break;
                case 5:
                    deleteEnrollment();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private void createEnrollment() {
        Long studentId = readLong("id estudiante: ");
        Long courseId = readLong("id curso: ");
        try {
            EnrollmentDTO dto = enrollmentService.createEnrollment(studentId, courseId);
            System.out.println("Matricula creada con id: " + dto.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findEnrollmentById() {
        Long id = readLong("id matricula: ");
        try {
            EnrollmentDTO dto = enrollmentService.findById(id);
            System.out.println(dto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAllEnrollments() {
        List<EnrollmentDTO> enrollments = enrollmentService.findAll();
        if (enrollments.isEmpty()) {
            System.out.println("No hay matriculas encontradas.");
        } else {
            enrollments.forEach(e -> System.out.println(e.toString()));
        }
    }

    private void cancelEnrollment() {
        Long id = readLong("Digite el id de la matricula para cancelar: ");
        try {
            EnrollmentDTO dto = enrollmentService.cancelEnrollment(id);
            System.out.println("Matricula cancelada: " + dto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteEnrollment() {
        Long id = readLong("Digite elid de la matricula a eliminar: ");
        try {
            enrollmentService.deleteEnrollment(id);
            System.out.println("Matricula eliminada.");
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
        return 0;
    }

    public void mostrar() {
    }
}