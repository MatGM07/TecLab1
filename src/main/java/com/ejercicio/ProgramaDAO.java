package com.ejercicio;

import java.util.List;

public interface ProgramaDAO {
    void insertar(Programa programa);
    Programa obtenerPorId(int id);
    List<Programa> obtenerTodos();
    void actualizar(Programa Programa);
    void eliminar(int id);
}
