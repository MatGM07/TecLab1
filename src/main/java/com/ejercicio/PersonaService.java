package com.ejercicio;

import java.sql.Connection;
import java.util.List;

public class PersonaService {
    private PersonaDAO personaDAO;

    public PersonaService(Connection conexion) {
        this.personaDAO = new PersonaDAOImplementacion(conexion);
    }

    public void registrarPersona(Persona persona) {
        personaDAO.insertar(persona);
    }

    public List<Persona> obtenerTodasLasPersonas() {
        return personaDAO.obtenerTodos();
    }

    public Persona obtenerPorId(int id) {
        return personaDAO.obtenerPorId(id);
    }

    public void actualizarPersona(Persona persona) {
        personaDAO.actualizar(persona);
    }

    public void eliminarPersona(int id) {
        personaDAO.eliminar(id);
    }
}
