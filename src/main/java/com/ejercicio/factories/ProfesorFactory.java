package com.ejercicio.factories;

import com.ejercicio.modelos.Profesor;

public class ProfesorFactory {
    public static Profesor crearProfesor(Integer id, String nombre, String apellidos, String email, String tipoContrato) {
        return new Profesor(id, nombre, apellidos, email, tipoContrato);
    }
}