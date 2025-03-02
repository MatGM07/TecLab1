package com.ejercicio.DAO;

import com.ejercicio.modelos.Facultad;

import java.util.List;

public interface FacultadDAO {
    void insertar(Facultad facultad);
    Facultad obtenerPorId(int id);
    List<Facultad> obtenerTodos();
    void actualizar(Facultad Facultad);
    void eliminar(int id);
}
