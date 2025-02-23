package com.ejercicio;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CursosProfesores implements Servicios {
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

    public void procesarArchivo(BufferedReader reader) throws IOException {
        CursoProfesor cursoProfesorActual = new CursoProfesor();
        Profesor profesorActual = new Profesor();
        Curso cursoActual = new Curso();
        Programa programaActual = new Programa();
        Facultad facultadActual = new Facultad();
        Persona decanoActual = new Persona();

        Map<String, Consumer<String>> procesadores = crearProcesadores(
                cursoProfesorActual, profesorActual, cursoActual, programaActual, facultadActual, decanoActual
        );

        String linea;
        while ((linea = reader.readLine()) != null) {
            procesarLinea(linea, procesadores);
        }
    }

    private Map<String, Consumer<String>> crearProcesadores(
            CursoProfesor cursoProfesorActual, Profesor profesorActual, Curso cursoActual,
            Programa programaActual, Facultad facultadActual, Persona decanoActual
    ) {
        Map<String, Consumer<String>> procesadores = new HashMap<>();

        procesadores.put("Año=", valor -> cursoProfesorActual.setAño(Integer.parseInt(valor)));
        procesadores.put("Semestre=", valor -> cursoProfesorActual.setSemestre(Integer.parseInt(valor)));
        procesadores.put("IDProfesor=", valor -> profesorActual.setID(Double.parseDouble(valor)));
        procesadores.put("nombreProfesor=", profesorActual::setNombre);
        procesadores.put("apellidosProfesor=", profesorActual::setApellidos);
        procesadores.put("emailProfesor=", profesorActual::setEmail);
        procesadores.put("TipoContrato=", profesorActual::setTipoContrato);
        procesadores.put("IDCurso=", valor -> cursoActual.setID(Integer.parseInt(valor)));
        procesadores.put("nombreCurso=", cursoActual::setNombre);
        procesadores.put("activo=", valor -> cursoActual.setActivo(Boolean.parseBoolean(valor)));
        procesadores.put("IDPrograma=", valor -> programaActual.setID(Double.parseDouble(valor)));
        procesadores.put("nombrePrograma=", programaActual::setNombre);
        procesadores.put("duracion=", valor -> programaActual.setDuracion(Double.parseDouble(valor)));
        procesadores.put("registro=", valor -> programaActual.setRegistro(Date.valueOf(valor)));
        procesadores.put("IDFacultad=", valor -> facultadActual.setID(Double.parseDouble(valor)));
        procesadores.put("nombreFacultad=", facultadActual::setNombre);
        procesadores.put("ID=", valor -> decanoActual.setID(Double.parseDouble(valor)));
        procesadores.put("Nombre=", decanoActual::setNombre);
        procesadores.put("Apellidos=", decanoActual::setApellidos);
        procesadores.put("Email=", valor -> {
            decanoActual.setEmail(valor);
            asignarRelaciones(cursoProfesorActual, profesorActual, cursoActual, programaActual, facultadActual, decanoActual);
        });

        return procesadores;
    }

    private void procesarLinea(String linea, Map<String, Consumer<String>> procesadores) {
        for (Map.Entry<String, Consumer<String>> entry : procesadores.entrySet()) {
            if (linea.startsWith(entry.getKey())) {
                entry.getValue().accept(linea.substring(entry.getKey().length()));
                return; 
            }
        }
    }

    private void asignarRelaciones(
            CursoProfesor cursoProfesorActual, Profesor profesorActual, Curso cursoActual,
            Programa programaActual, Facultad facultadActual, Persona decanoActual
    ) {
        facultadActual.setDecano(decanoActual);
        programaActual.setFacultad(facultadActual);
        cursoActual.setPrograma(programaActual);
        cursoProfesorActual.setProfesor(profesorActual);
        cursoProfesorActual.setCurso(cursoActual);
        listado.add(cursoProfesorActual);
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

    @Override
    public Integer cantidadActual(){
        Integer cantidadCursosProfesores = listado.size();
        return cantidadCursosProfesores;
    }

    @Override
    public String imprimirPosicion(Integer posicion){
        String contenidoPosicion = listado.get(posicion).toString();
        System.out.println(contenidoPosicion);
        return  contenidoPosicion;
    }

    @Override
    public List<String> imprimirListado(){
        List<String> listadoString = toStringList();
        System.out.println(listadoString);
        return  listadoString;
    }



}
