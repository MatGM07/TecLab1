package com.ejercicio;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.ProgramaService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "zzz");

            ProgramaService programaService = new ProgramaService(conexion);
            EstudianteService estudianteService = new EstudianteService(conexion);

            estudianteService.eliminarEstudiante(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}

