package com.ejercicio;

import java.util.List;

public interface FacultadDAO {
    void insertar(Facultad facultad);
    Facultad obtenerPorId(int id);
    List<Facultad> obtenerTodos();
    void actualizar(Facultad Facultad);
    void eliminar(int id);
}
