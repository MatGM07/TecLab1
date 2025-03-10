package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.modelos.Programa;

import java.sql.Connection;

public class ProgramaController {
    ProgramaService programaService;

    public ProgramaController(Connection conexion){
        this.programaService = new ProgramaService(conexion);
    }

    public Programa obtenerPorId(int id){
        return programaService.obtenerPorId(id);
    }

}
