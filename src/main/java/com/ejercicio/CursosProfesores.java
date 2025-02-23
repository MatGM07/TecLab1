package com.ejercicio;

import java.io.*;
import java.sql.Date;
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

    public void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CursosProfesores.txt"))) {
            procesarArchivo(reader);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void procesarArchivo(BufferedReader reader) throws IOException {
        String linea;
        CursoProfesor cursoProfesorActual = null;
        Profesor profesorActual = null;
        Curso cursoActual= null;
        Programa programaActual= null;
        Facultad facultadActual= null;
        Persona decanoActual= null;

        while ((linea = reader.readLine()) != null) {
            if (linea.startsWith("Año=")) {
                cursoProfesorActual = new CursoProfesor();
                cursoProfesorActual.setAño(Integer.parseInt(linea.substring(4)));
            } else if (linea.startsWith("Semestre=")) {
                cursoProfesorActual.setSemestre(Integer.parseInt(linea.substring(9)));
            } else if (linea.startsWith("IDProfesor=")) {
                profesorActual = new Profesor();
                profesorActual.setID(Double.parseDouble(linea.substring(11)));
            } else if (linea.startsWith("nombreProfesor=")) {
                profesorActual.setNombre(linea.substring(15));
            } else if (linea.startsWith("apellidosProfesor=")) {
                profesorActual.setApellidos(linea.substring(19));
            } else if (linea.startsWith("emailProfesor=")) {
                profesorActual.setEmail(linea.substring(14));
            } else if (linea.startsWith("TipoContrato=")) {
                profesorActual.setTipoContrato(linea.substring(13));
            } else if (linea.startsWith("IDCurso=")) {
                cursoActual = new Curso();
                cursoActual.setID(Integer.parseInt(linea.substring(8)));
            } else if (linea.startsWith("nombreCurso=")) {
                cursoActual.setNombre(linea.substring(12));
            } else if (linea.startsWith("activo=")){
                cursoActual.setActivo(Boolean.valueOf(linea.substring(7)));
            } else if (linea.startsWith("IDPrograma=")) {
                programaActual = new Programa();
                programaActual.setID(Double.parseDouble(linea.substring(11)));
            } else if (linea.startsWith("nombrePrograma=")) {
                programaActual.setNombre(linea.substring(15));
            } else if (linea.startsWith("duracion=")) {
                programaActual.setDuracion(Double.parseDouble(linea.substring(9)));
            } else if (linea.startsWith("registro=")) {
                programaActual.setRegistro(Date.valueOf(linea.substring(9)));
            } else if (linea.startsWith("IDFacultad=")) {
                facultadActual = new Facultad();
                facultadActual.setID(Double.parseDouble(linea.substring(11)));
            } else if (linea.startsWith("nombreFacultad=")) {
                facultadActual.setNombre(linea.substring(15));
            } else if (linea.startsWith("ID=")) {
                decanoActual = new Persona();
                decanoActual.setID(Double.parseDouble(linea.substring(3)));
            } else if (linea.startsWith("Nombre=")) {
                decanoActual.setNombre(linea.substring(7));
            } else if (linea.startsWith("Apellidos=")) {
                decanoActual.setApellidos(linea.substring(10));
            } else if (linea.startsWith("Email=")) {
                decanoActual.setEmail(linea.substring(6));
                facultadActual.setDecano(decanoActual);
                programaActual.setFacultad(facultadActual);
                cursoActual.setPrograma(programaActual);
                cursoProfesorActual.setProfesor(profesorActual);
                cursoProfesorActual.setCurso(cursoActual);
                listado.add(cursoProfesorActual);
            }
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

    public List<String> toStringList() {
        List<String> resultado = new ArrayList<>();
        for (CursoProfesor cp : listado) {
            resultado.add(cp.toString());
        }
        return resultado;
    }



}
