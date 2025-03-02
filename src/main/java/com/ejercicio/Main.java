package com.ejercicio;

import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "clave");

            FacultadService facultadService = new FacultadService(conexion);
            ProgramaService programaService = new ProgramaService(conexion);


            programaService.eliminarPrograma(2);


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}

