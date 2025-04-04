package com.ejercicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static ConexionDB instancia;
    private static Connection conexion;

    private static String url;
    private static String usuario;
    private static String contraseña;

    private ConexionDB() {
        cargarCredenciales();
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con la base de datos");
        }
    }

    public static ConexionDB getInstancia() {
        if (instancia == null) {
            synchronized (ConexionDB.class) {
                if (instancia == null) {
                    instancia = new ConexionDB();
                }
            }
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    private void cargarCredenciales() {
        Properties propiedades = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            propiedades.load(reader);
            url = propiedades.getProperty("url");
            usuario = propiedades.getProperty("usuario");
            contraseña = propiedades.getProperty("contraseña");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el archivo de configuración");
        }
    }
}