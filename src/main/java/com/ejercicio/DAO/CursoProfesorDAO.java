package com.ejercicio.DAO;

import com.ejercicio.modelos.CursoProfesor;

import java.util.List;

public interface CursoProfesorDAO {
    void insertar(CursoProfesor cursoProfesor);
    CursoProfesor obtenerPorId(int profesorId, int cursoId);
    List<CursoProfesor> obtenerTodos();
    void actualizar(CursoProfesor cursoProfesor);
    void eliminar(int profesorId, int cursoId);
}
