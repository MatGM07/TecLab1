package com.ejercicio.factories;

import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Inscripción;

public class InscripcionFactory {
    public static Inscripción crearInscripcion(Estudiante estudiante, Curso curso, Integer anio, Integer semestre) {
        return new Inscripción(curso, anio, semestre, estudiante);
    }
}