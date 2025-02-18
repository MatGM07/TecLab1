package com.ejercicio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores {
    private List<CursoProfesor> listado = new ArrayList<>();


    public CursosProfesores(List<CursoProfesor> listado) {
        this.listado = listado;
    }
    public CursosProfesores(){
    }

    public List<CursoProfesor> getListado() {
        return listado;
    }

    public void setListado(List<CursoProfesor> listado) {
        this.listado = listado;
    }

    public void inscribir(CursoProfesor cursoProfesor){
        listado.add(cursoProfesor);
    }

    public void cargarDatos(){
        String nombreArchivo = "CursosProfesores.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    public void guardarInformacion(CursoProfesor cursoProfesor){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("CursosProfesores.txt", true));
            writer.write("\n");
            writer.write(cursoProfesor.toString());
            writer.write("\n");
            writer.close();
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }


}
