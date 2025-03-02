package com.ejercicio;

import java.util.List;

public interface EstudianteDAO {
    void insertar(Estudiante estudiante);
    Estudiante obtenerPorId(int id);
    List<Estudiante> obtenerTodos();
    void actualizar(Estudiante estudiante);
    void eliminar(int id);
}
