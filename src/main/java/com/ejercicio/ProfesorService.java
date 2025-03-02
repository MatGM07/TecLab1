package com.ejercicio;

import java.sql.Connection;
import java.util.List;

public class ProfesorService {
    private ProfesorDAO profesorDAO;

    public ProfesorService(Connection conexion) {
        this.profesorDAO = new ProfesorDAOImplementacion(conexion);
    }

    public void registrarProfesor(Profesor profesor) {
        profesorDAO.insertar(profesor);
    }

    public List<Profesor> obtenerTodosLosProfesores() {
        return profesorDAO.obtenerTodos();
    }

    public Profesor buscarPorId(int id) {
        return profesorDAO.obtenerPorId(id);
    }

    public void actualizarProfesor(Profesor profesor) {
        profesorDAO.actualizar(profesor);
    }

    public void eliminarProfesor(int id) {
        profesorDAO.eliminar(id);
    }
}
