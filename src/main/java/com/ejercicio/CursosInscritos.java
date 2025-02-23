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

    public void guardarInformacion(Inscripción inscripcion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Inscripciones.txt", true))) {
            writer.write(inscripcion.toString() + "\n");
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }
}

