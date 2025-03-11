package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.modelos.Programa;
import com.ejercicio.modelos.Facultad;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProgramaController {
    ProgramaService programaService;
    FacultadController facultadController;

    public ProgramaController(Connection conexion){
        this.programaService = new ProgramaService(conexion);
        this.facultadController = new FacultadController(conexion);
    }

    public Programa obtenerPorId(int id){
        return programaService.obtenerPorId(id);
    }

    public List<String> obtenerDatosPorId(int id){
        Programa programa = programaService.obtenerPorId(id);
        String nombre = programa.getNombre();
        String duracion = String.valueOf(programa.getDuracion());
        String registro = String.valueOf(programa.getRegistro());
        String facultad_id = String.valueOf(programa.getFacultad().getID());

        List<String> datos = new ArrayList<>();

        datos.add(nombre);
        datos.add(duracion);
        datos.add(registro);
        datos.add(facultad_id);

        return datos;
    }

    public void agregar(String nombre, Double duracion, Date registro, Integer facultad_id){
        Facultad facultad = facultadController.obtenerPorId(facultad_id);

        Programa programa = new Programa(null,nombre,duracion,registro,facultad);

        programaService.registrarPrograma(programa);
    }

    public Boolean existe(Integer id){
        Programa programa = programaService.obtenerPorId(id);
        if (programa == null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodosLosProgramas(){
        List<Programa> programas = programaService.obtenerTodosLosProgramas();
        List<List<String>> datosTodosProgramas = new ArrayList<>();
        for (Programa p: programas){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(p.getID()));
            datos.add(p.getNombre());
            datos.add(String.valueOf(p.getDuracion()));
            datos.add(String.valueOf(p.getRegistro()));
            datos.add(String.valueOf(p.getFacultad().getID()));
            datosTodosProgramas.add(datos);
        }
        return datosTodosProgramas;
    }

    public void actualizar(Integer id, String nombre, Double duracion, Date registro, Integer facultad_id){
        Facultad facultad = facultadController.obtenerPorId(facultad_id);

        Programa programa = new Programa(id,nombre,duracion,registro,facultad);

        programaService.actualizarPrograma(programa);
    }

    public void eliminar(Integer id){
        programaService.eliminarPrograma(id);
    }

}
