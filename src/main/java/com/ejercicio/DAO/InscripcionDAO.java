package com.ejercicio.DAO;

import com.ejercicio.modelos.Inscripción;

import java.util.List;

public interface InscripcionDAO {
    void insertar(Inscripción inscripcion);
    Inscripción obtenerPorId(int estudianteId, int cursoId);
    List<Inscripción> obtenerTodos();
    void actualizar(Inscripción inscripcion);
    void eliminar(int estudianteId, int cursoId);
}
