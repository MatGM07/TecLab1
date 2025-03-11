package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Persona;
import com.ejercicio.modelos.Programa;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FacultadController {
    FacultadService facultadService;
    PersonaController personaController;

    public FacultadController(Connection conexion){
        this.facultadService = new FacultadService(conexion);
    }

    public Facultad obtenerPorId(int id){
        return facultadService.obtenerPorId(id);
    }

    public List<String> obtenerDatosPorId(int id){
        Facultad facultad = facultadService.obtenerPorId(id);
        String nombre = facultad.getNombre();
        String decano_id = String.valueOf(facultad.getDecano().getID());

        List<String> datos = new ArrayList<>();

        datos.add(nombre);
        datos.add(decano_id);

        return datos;
    }

    public void agregar(String nombre, Integer decano_id){
        Persona decano = personaController.obtenerPorId(decano_id);

        Facultad facultad = new Facultad(null, nombre, decano);

        facultadService.registrarFacultad(facultad);
    }

    public Boolean existe(Integer id){
        Facultad facultad = facultadService.obtenerPorId(id);
        if (facultad == null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodasLasFacultades(){
        List<Facultad> facultades = facultadService.obtenerTodasLasFacultades();
        List<List<String>> datosTodosFacultades = new ArrayList<>();
        for (Facultad f: facultades){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(f.getID()));
            datos.add(f.getNombre());
            datos.add(String.valueOf(f.getDecano().getID()));
            datosTodosFacultades.add(datos);
        }
        return datosTodosFacultades;
    }

    public void actualizar(Integer id, String nombre, Integer decano_id){
        Persona decano = personaController.obtenerPorId(decano_id);

        Facultad facultad = new Facultad(id, nombre, decano);

        facultadService.actualizarFacultad(facultad);
    }

    public void eliminar(Integer id){
        facultadService.eliminarFacultad(id);
    }


}
