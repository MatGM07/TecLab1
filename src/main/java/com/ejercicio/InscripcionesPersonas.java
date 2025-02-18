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

public class InscripcionesPersonas {
    private List<Persona> listado = new ArrayList<>();


    public InscripcionesPersonas(List<Persona> listado) {
        this.listado = listado;
    }

    public InscripcionesPersonas(){}

    public List<Persona> getListado() {
        return listado;
    }

    public void setListado(List<Persona> listado) {
        this.listado = listado;
    }

    public void inscribir(Persona persona){
        listado.add(persona);
    }

    public Optional<Integer> encontrar(Persona persona){
        for (Integer i = 0; i < listado.size(); i++){
            Double IDaBuscar = persona.getID();
            Double IDListado = listado.get(i).getID();
            if (Objects.equals(IDaBuscar, IDListado)){
                return Optional.of(i);
            }
        }
        System.out.println("Objeto a eliminar no encontrado");
        return Optional.empty();
        
    }

    public void eliminar(Persona persona){
        Optional<Integer> PosicionObjetivo = encontrar(persona);
        if (!PosicionObjetivo.isEmpty()){
            int index = PosicionObjetivo.get();
            listado.remove(index);
        }else {
            System.out.println("Objeto a eliminar no eliminado");
        }
    }

    public void actualizar(Persona persona){
        Optional<Integer> PosicionObjetivo = encontrar(persona);
        if (!PosicionObjetivo.isEmpty()){
            Persona personaActualizada = listado.get(PosicionObjetivo.get());
            personaActualizada.setNombre(persona.getNombre());
            personaActualizada.setApellidos(persona.getApellidos());
            personaActualizada.setEmail(persona.getEmail());
        }else{
            System.out.println("Persona a actualizar no encontrada");
        }
    }

    public void guardarInformacion(Persona persona){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Personas.txt", true));
            writer.write("\n");
            writer.write(persona.toString());
            writer.write("\n");
            writer.close();
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }

    public Void cargarDatos(){
        String nombreArchivo = "Personas.txt";
        Double nuevoIDDouble = null;
        String nuevoNombre = null;
        String nuevosApellidos = null;
        String nuevoEmail = null;
        Persona nuevaPersona = new Persona();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("ID=")){
                    String nuevoID = linea.substring(3);
                    nuevoIDDouble = Double.parseDouble(nuevoID);
                    nuevaPersona.setID(nuevoIDDouble);
                } else if (linea.contains("Nombre=")) {
                    nuevoNombre = linea.substring(7);
                    nuevaPersona.setNombre(nuevoNombre);
                }else if (linea.contains("Apellidos=")) {
                    nuevosApellidos = linea.substring(10);
                    nuevaPersona.setApellidos(nuevosApellidos);
                }else if (linea.contains("Email=")) {
                    nuevoEmail = linea.substring(6);
                    nuevaPersona.setEmail(nuevoEmail);
                    this.listado.add(nuevaPersona);
                }
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        return null;
    }


}
