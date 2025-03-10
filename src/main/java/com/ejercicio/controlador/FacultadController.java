package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Facultad;

import java.sql.Connection;
import java.sql.Date;

public class FacultadController {
    FacultadService facultadService;

    public FacultadController(Connection conexion){
        this.facultadService = new FacultadService(conexion);
    }

    public Facultad obtenerPorId(int id){
        return facultadService.obtenerPorId(id);
    }
}
