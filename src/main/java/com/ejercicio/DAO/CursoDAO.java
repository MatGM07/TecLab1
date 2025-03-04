package com.ejercicio.DAO;

import com.ejercicio.modelos.Curso;

import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso);
    Curso obtenerPorId(int id);
    List<Curso> obtenerTodos();
    void actualizar(Curso Curso);
    void eliminar(int id);

}