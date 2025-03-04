package com.ejercicio.DAOServicios;

import com.ejercicio.DAO.ProfesorDAO;

import java.sql.Connection;
import com.ejercicio.DAOImplementacion.ProfesorDAOImplementacion;
import com.ejercicio.modelos.Profesor;

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

    public Profesor obtenerPorId(int id) {
        return profesorDAO.obtenerPorId(id);
    }

    public void actualizarProfesor(Profesor profesor) {
        profesorDAO.actualizar(profesor);
    }

    public void eliminarProfesor(int id) {
        profesorDAO.eliminar(id);
    }
}
