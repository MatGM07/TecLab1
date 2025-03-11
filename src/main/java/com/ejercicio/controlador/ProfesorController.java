package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.modelos.Persona;
import com.ejercicio.modelos.Profesor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProfesorController {
    ProfesorService profesorService;

    public ProfesorController(Connection connection){
        this.profesorService = new ProfesorService(connection);
    }

    public Profesor obtenerPorId(Integer id){
        return profesorService.obtenerPorId(id);
    }

    public List<String> obtenerDatosPorId(Integer id){
        Profesor profesor = profesorService.obtenerPorId(id);
        String nombre = profesor.getNombre();
        String apellidos = profesor.getApellidos();
        String email = profesor.getEmail();
        String tipoContrato = profesor.getTipoContrato();

        List<String> datos = new ArrayList<>();

        datos.add(nombre);
        datos.add(apellidos);
        datos.add(email);
        datos.add(tipoContrato);

        return datos;
    }

    public void agregar(String nombre, String apellidos, String email, String tipoContrato){
        Profesor profesor = new Profesor(null,nombre,apellidos,email,tipoContrato);
        profesorService.registrarProfesor(profesor);
    }

    public Boolean existe(Integer id){
        Profesor profesor = profesorService.obtenerPorId(id);
        if (profesor== null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodasLosProfesores(){
        List<Profesor> profesores = profesorService.obtenerTodosLosProfesores();
        List<List<String>> datosTodosPersonas = new ArrayList<>();
        for (Profesor p: profesores){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(p.getID()));
            datos.add(p.getNombre());
            datos.add(p.getApellidos());
            datos.add(p.getEmail());
            datos.add(p.getTipoContrato());
            datosTodosPersonas.add(datos);
        }
        return datosTodosPersonas;
    }

    public void actualizar(Integer id, String nombre, String apellidos, String email, String tipoContrato){
        Profesor profesor = new Profesor(id,nombre,apellidos,email,tipoContrato);
        profesorService.actualizarProfesor(profesor);
    }

    public void eliminar(Integer id){
        profesorService.eliminarProfesor(id);
    }
}
