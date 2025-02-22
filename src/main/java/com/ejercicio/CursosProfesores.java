package com.ejercicio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores {
    private List<CursoProfesor> listado = new ArrayList<>();

    public CursosProfesores(List<CursoProfesor> listado) {
        this.listado = listado;
    }

    public CursosProfesores(){}

    public List<CursoProfesor> getListado() {
        return listado;
    }

    public void setListado(List<CursoProfesor> listado) {
        this.listado = listado;
    }

    public void inscribir(CursoProfesor cursoProfesor){
        listado.add(cursoProfesor);
    }
    /*
       public void cargarDatos() {
           try (BufferedReader reader = new BufferedReader(new FileReader("Personas.txt"))) {
               procesarArchivo(reader);
           } catch (IOException e) {
               System.err.println("Error al leer el archivo: " + e.getMessage());
           }
       }

           private void procesarArchivo(BufferedReader reader) throws IOException {
               String linea;
               CursoProfesor cursoProfesorActual = null;

               while ((linea = reader.readLine()) != null) {
                   if (linea.startsWith("Profesor=")) {
                       cursoProfesorActual = new CursoProfesor();
                       Double profesorID = Double.parseDouble(linea.substring(3));
                       cursoProfesorActual.setID(Double.parseDouble(linea.substring(3)));
                   } else if (cursoProfesorActual != null) {
                       asignarDatoCursoProfesores(cursoProfesorActual, linea);
                   }
               }
           }

           private void asignarDatoCursoProfesores(CursoProfesor cursoProfesor, String linea) {
               if (linea.startsWith("Profesor=")) {
                   cursoProfesor.setProfesor(linea.substring(7));
               } else if (linea.startsWith("Apellidos=")) {
                   persona.setApellidos(linea.substring(10));
               } else if (linea.startsWith("Email=")) {
                   persona.setEmail(linea.substring(6));
                   listado.add(persona);
               }
           }
       */
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

    public List<String> toStringList() {
        List<String> resultado = new ArrayList<>();
        for (CursoProfesor cp : listado) {
            resultado.add(cp.toString());
        }
        return resultado;
    }
}
