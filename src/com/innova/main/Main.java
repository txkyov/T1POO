package com.innova.main;

import com.innova.controller.AlumnoController;
import com.innova.model.Alumno;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlumnoController controller = new AlumnoController();
        int opcion = 0;

        System.out.println("===============================================");
        System.out.println("   SISTEMA DE GESTIÓN - INSTITUTO INNOVA");
        System.out.println("===============================================");

        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Registrar nuevo alumno");
            System.out.println("2. Listar alumnos registrados");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarAlumno(scanner, controller);
                        break;
                    case 2:
                        controller.listarAlumnos();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Error: Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }

        } while (opcion != 3);

        scanner.close();
    }

    private static void registrarAlumno(Scanner scanner, AlumnoController controller) {
        try {
            System.out.println("\n--- Registro de Alumno ---");
            
            System.out.print("Ingrese nombre completo: ");
            String nombre = scanner.nextLine();

            System.out.print("Tipo de documento (DNI / Residencia Temporal): ");
            String tipoDocumento = scanner.nextLine();

            System.out.print("Número de documento: ");
            String numeroDocumento = scanner.nextLine();

            System.out.print("Nivel socioeconómico (A, B, C): ");
            String nivelInput = scanner.nextLine();
            if (nivelInput.isEmpty()) throw new IllegalArgumentException("El nivel no puede estar vacío.");
            char nivelSocioeconomico = nivelInput.charAt(0);

            System.out.print("Tipo de beca (Ninguna / Parcial / Total): ");
            String tipoBeca = scanner.nextLine();

            Alumno nuevoAlumno = new Alumno(nombre, tipoDocumento, numeroDocumento, nivelSocioeconomico, tipoBeca);
            controller.agregarAlumno(nuevoAlumno);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("\n[ERROR DE VALIDACIÓN] " + e.getMessage());
            System.out.println("El alumno no fue registrado. Inténtelo de nuevo.");
        }
    }
}
