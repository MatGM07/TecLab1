package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.Persona;

import java.sql.Connection;

public class PersonaController {
    PersonaService personaService;

    public PersonaController(Connection conexion){
        this.personaService = new PersonaService(conexion);
    }

    public Persona obtenerPorId(int id){
        return  personaService.obtenerPorId(id);
    }
}
