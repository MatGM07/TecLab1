package com.ejercicio;

import java.sql.Connection;
import java.util.List;

public class FacultadService {
    private FacultadDAO facultadDAO;
    private PersonaService personaService;

    public FacultadService(Connection conexion) {
        this.facultadDAO = new FacultadDAOImplementacion(conexion);
        this.personaService = new PersonaService(conexion);
    }

    public void registrarFacultad(Facultad facultad) {
        facultadDAO.insertar(facultad);
    }

    public Facultad obtenerPorId(int id) {
        Facultad facultad = facultadDAO.obtenerPorId(id);
        if (facultad != null && facultad.getDecano().getID() > 0) {
            Persona decanoCompleto = personaService.buscarPorId(facultad.getDecano().getID());
            facultad.setDecano(decanoCompleto);
        }
        return facultad;
    }

    public List<Facultad> obtenerTodasLasFacultades() {
        List<Facultad> facultades = facultadDAO.obtenerTodos();
        for (Facultad facultad : facultades){
            if (facultad != null && facultad.getDecano().getID() > 0) {
                Persona decanoCompleto = new Persona();
                decanoCompleto = personaService.buscarPorId(facultad.getDecano().getID());
                facultad.setDecano(decanoCompleto);
            }
        }
        return facultades;
    }

    public void actualizarFacultad(Facultad facultad) {
        facultadDAO.actualizar(facultad);
    }

    public void eliminarFacultad(int id) {
        facultadDAO.eliminar(id);
    }
}
