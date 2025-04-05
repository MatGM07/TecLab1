package com.ejercicio.factories;

import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Programa;

public class EstudianteFactory {
    public static Estudiante crearEstudiante(Integer id, String nombres, String apellidos, String email,
                                             Double codigo, Programa programa, Boolean activo, Double promedio) {
        return new Estudiante(id, nombres, apellidos, email, codigo, programa, activo, promedio);
    }
}
