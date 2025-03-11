package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.Persona;

import java.io.StringReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {
    PersonaService personaService;

    public PersonaController(Connection conexion){
        this.personaService = new PersonaService(conexion);
    }

    public Persona obtenerPorId(int id){
        return  personaService.obtenerPorId(id);
    }

    public List<String> obtenerDatosPorId(Integer id){
        Persona persona = personaService.obtenerPorId(id);
        String nombre = persona.getNombre();
        String apellidos = persona.getApellidos();
        String email = persona.getEmail();

        List<String> datos = new ArrayList<>();

        datos.add(nombre);
        datos.add(apellidos);
        datos.add(email);

        return datos;
    }

    public void agregar(String nombre, String apellidos, String email){
        Persona persona = new Persona(null, nombre, apellidos, email);
        personaService.registrarPersona(persona);
    }

    public Boolean existe(Integer id){
        Persona persona = personaService.obtenerPorId(id);
        if (persona== null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodasLasPersonas(){
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        List<List<String>> datosTodosPersonas = new ArrayList<>();
        for (Persona f: personas){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(f.getID()));
            datos.add(f.getNombre());
            datos.add(f.getApellidos());
            datos.add(f.getEmail());
            datosTodosPersonas.add(datos);
        }
        return datosTodosPersonas;
    }

    public void actualizar(Integer id, String nombre, String apellidos, String email){
        Persona persona = new Persona(id, nombre, apellidos, email);
        personaService.actualizarPersona(persona);
    }

    public void eliminar(Integer id){
        personaService.eliminarPersona(id);
    }


}
