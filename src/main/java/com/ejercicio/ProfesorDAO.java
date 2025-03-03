package com.ejercicio;

import java.util.List;

public interface ProfesorDAO {
    void insertar(Profesor profesor);
    Profesor obtenerPorId(int id);
    List<Profesor> obtenerTodos();
    void actualizar(Profesor profesor);
    void eliminar(int id);
}
