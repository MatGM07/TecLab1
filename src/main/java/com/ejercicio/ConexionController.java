package com.ejercicio;

import java.sql.Connection;

public class ConexionController {
    public static Connection obtenerConexion() {
        return ConexionDB.getInstancia().getConexion();
    }
}
