package com.innova.controller;

import com.innova.model.Alumno;
import java.util.ArrayList;
import java.util.List;

public class AlumnoController {
    private List<Alumno> listaAlumnos;

    public AlumnoController() {
        this.listaAlumnos = new ArrayList<>();
    }

    public void agregarAlumno(Alumno alumno) {
        if (alumno != null) {
            // Validación de homonimia simple (mismo nombre y documento)
            for (Alumno a : listaAlumnos) {
                if (a.getNumeroDocumento().equals(alumno.getNumeroDocumento())) {
                    throw new IllegalArgumentException("Ya existe un alumno registrado con el mismo número de documento.");
                }
            }
            listaAlumnos.add(alumno);
            System.out.println("Alumno registrado exitosamente.");
        }
    }

    public void listarAlumnos() {
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados en el sistema.");
        } else {
            System.out.println("--- Lista de Alumnos ---");
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println((i + 1) + ". " + listaAlumnos.get(i).toString());
            }
            System.out.println("------------------------");
        }
    }
}
