package com.ejercicio.DAO;

import com.ejercicio.modelos.Persona;

import java.util.List;

public interface PersonaDAO {
    void insertar(Persona persona);
    Persona obtenerPorId(int id);
    List<Persona> obtenerTodos();
    void actualizar(Persona Persona);
    void eliminar(int id);
}
