package com.ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.function.Consumer;
import java.util.HashMap;

public class CursosInscritos {
    private List<Inscripción> listado = new ArrayList<>();

    public CursosInscritos(List<Inscripción> listado) {
        this.listado = listado;
    }

    public CursosInscritos() {}

    public List<Inscripción> getListado() {
        return listado;
    }

    public void setListado(List<Inscripción> listado) {
        this.listado = listado;
    }

    public void inscribirCurso(Inscripción inscripcion) {
        listado.add(inscripcion);
    }

    public Optional<Integer> encontrar(Inscripción inscripcion) {
        for (Integer i = 0; i < listado.size(); i++) {
            if (Objects.equals(listado.get(i).getCurso().getID(), inscripcion.getCurso().getID()) &&
                    Objects.equals(listado.get(i).getEstudiante().getID(), inscripcion.getEstudiante().getID())) {
                return Optional.of(i);
            }
        }
        System.out.println("Inscripción no encontrada");
        return Optional.empty();
    }

    public void eliminar(Inscripción inscripcion) {
        Optional<Integer> posicionObjetivo = encontrar(inscripcion);
        if (posicionObjetivo.isPresent()) {
            listado.remove(posicionObjetivo.get().intValue());
        } else {
            System.out.println("Inscripción no eliminada");
        }
    }

    public void actualizar(Inscripción inscripcion) {
        Optional<Integer> posicionObjetivo = encontrar(inscripcion);
        if (posicionObjetivo.isPresent()) {
            Inscripción inscripcionActualizada = listado.get(posicionObjetivo.get());
            inscripcionActualizada.setAño(inscripcion.getAño());
            inscripcionActualizada.setSemestre(inscripcion.getSemestre());
        } else {
            System.out.println("Inscripción a actualizar no encontrada");
        }
    }

    public void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CursosInscritos.txt"))) {
            procesarArchivo(reader);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void procesarArchivo(BufferedReader reader) throws IOException {
        Inscripción inscripcionActual = new Inscripción();
        Estudiante estudianteActual = new Estudiante();
        Curso cursoActual = new Curso();
        Programa programaActual = new Programa();
        Facultad facultadActual = new Facultad();
        Persona decanoActual = new Persona();

        Map<String, Consumer<String>> procesadores = crearProcesadores(
                inscripcionActual, estudianteActual, cursoActual, programaActual, facultadActual, decanoActual
        );

        String linea;
        while ((linea = reader.readLine()) != null) {
            procesarLinea(linea, procesadores);
        }
    }

    private Map<String, Consumer<String>> crearProcesadores(
            Inscripción inscripcionActual, Estudiante estudianteActual, Curso cursoActual,
            Programa programaActual, Facultad facultadActual, Persona decanoActual
    ) {
        Map<String, Consumer<String>> procesadores = new HashMap<>();

        procesadores.put("Año=", valor -> inscripcionActual.setAño(Integer.parseInt(valor)));
        procesadores.put("Semestre=", valor -> inscripcionActual.setSemestre(Integer.parseInt(valor)));
        procesadores.put("IDEstudiante=", valor -> estudianteActual.setID(Double.parseDouble(valor)));
        procesadores.put("nombreEstudiante=", estudianteActual::setNombre);
        procesadores.put("apellidosEstudiante=", estudianteActual::setApellidos);
        procesadores.put("emailEstudiante=", estudianteActual::setEmail);
        procesadores.put("codigoEstudiante=", valor -> estudianteActual.setCodigo(Double.parseDouble(valor)));
        procesadores.put("activoEstudiante=", valor -> estudianteActual.setActivo(Boolean.parseBoolean(valor)));
        procesadores.put("promedioEstudiante=", valor -> estudianteActual.setPromedio(Double.parseDouble(valor)));
        procesadores.put("IDCurso=", valor -> cursoActual.setID(Integer.parseInt(valor)));
        procesadores.put("nombreCurso=", cursoActual::setNombre);
        procesadores.put("activoCurso=", valor -> cursoActual.setActivo(Boolean.parseBoolean(valor)));
        procesadores.put("IDPrograma=", valor -> programaActual.setID(Double.parseDouble(valor)));
        procesadores.put("nombrePrograma=", programaActual::setNombre);
        procesadores.put("IDFacultad=", valor -> facultadActual.setID(Double.parseDouble(valor)));
        procesadores.put("nombreFacultad=", facultadActual::setNombre);
        procesadores.put("IDDecano=", valor -> decanoActual.setID(Double.parseDouble(valor)));
        procesadores.put("NombreDecano=", decanoActual::setNombre);
        procesadores.put("ApellidosDecano=", decanoActual::setApellidos);
        procesadores.put("EmailDecano=", valor -> {
            decanoActual.setEmail(valor);
            asignarRelaciones(inscripcionActual, estudianteActual, cursoActual, programaActual, facultadActual, decanoActual);
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
            Inscripción inscripcionActual, Estudiante estudianteActual, Curso cursoActual,
            Programa programaActual, Facultad facultadActual, Persona decanoActual
    ) {
        facultadActual.setDecano(decanoActual);
        programaActual.setFacultad(facultadActual);
        cursoActual.setPrograma(programaActual);
        inscripcionActual.setEstudiante(estudianteActual);
        inscripcionActual.setCurso(cursoActual);
        listado.add(inscripcionActual);
    }


    public void guardarInformacion(Inscripción inscripcion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Inscripciones.txt", true))) {
            writer.write(inscripcion.toString() + "\n");
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }
}


