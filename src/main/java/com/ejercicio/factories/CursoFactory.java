package com.ejercicio.factories;

import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Programa;

public class CursoFactory {
    public static Curso crearCurso(Integer id, String nombre, Boolean activo, Programa programa) {
        return new Curso(id, activo, programa, nombre);
    }
}