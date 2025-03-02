package com.ejercicio;

import java.sql.Connection;
import java.util.List;

public class ProgramaService {
    private ProgramaDAO programaDAO;
    private FacultadService facultadService;

    public ProgramaService(Connection conexion) {
        this.programaDAO = new ProgramaDAOImplementacion(conexion);
        this.facultadService = new FacultadService(conexion);
    }

    public void registrarPrograma(Programa programa) {
        programaDAO.insertar(programa);
    }

    public Programa obtenerPorId(int id) {
        Programa programa = programaDAO.obtenerPorId(id);
        if (programa != null && programa.getFacultad().getID() > 0) {
            Facultad facultadCompleta = facultadService.obtenerPorId(programa.getFacultad().getID());
            programa.setFacultad(facultadCompleta);
        }
        return programa;
    }

    public List<Programa> obtenerTodosLosProgramas() {
        List<Programa> programas = programaDAO.obtenerTodos();
        for (Programa programa : programas){
            if (programa != null && programa.getFacultad().getID() > 0) {
                Facultad facultadCompleta = new Facultad();
                facultadCompleta = facultadService.obtenerPorId(programa.getFacultad().getID());
                programa.setFacultad(facultadCompleta);
            }
        }
        return programas;
    }

    public void actualizarPrograma(Programa programa) {
        programaDAO.actualizar(programa);
    }

    public void eliminarPrograma(int id) {
        programaDAO.eliminar(id);
    }
}
