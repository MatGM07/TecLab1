package com.ejercicio.modelos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            Integer IDaBuscar = persona.getID();
            Integer IDListado = listado.get(i).getID();
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

    public void guardarInformacion() {
        File archivo = new File("Personas.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, false))) {
            for (Persona persona : listado) {
                writer.println("---------------------------");
                writer.println(persona.toString());
                writer.println("---------------------------");
            }
            System.out.println("Todas las personas fueron guardadas exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Personas.txt"))) {
            procesarArchivo(reader);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void procesarArchivo(BufferedReader reader) throws IOException {
        String linea;
        Persona personaActual = null;

        while ((linea = reader.readLine()) != null) {
            if (linea.startsWith("ID=")) {
                personaActual = new Persona();
                personaActual.setID(Integer.parseInt(linea.substring(3)));
            } else if (personaActual != null) {
                asignarDatoPersona(personaActual, linea);
            }
        }
    }
    private void asignarDatoPersona(Persona persona, String linea) {
        if (linea.startsWith("Nombre=")) {
            persona.setNombre(linea.substring(7));
        } else if (linea.startsWith("Apellidos=")) {
            persona.setApellidos(linea.substring(10));
        } else if (linea.startsWith("Email=")) {
            persona.setEmail(linea.substring(6));
            listado.add(persona);
        }
    }

    public String imprimirPosicion(Integer posicion){
        String contenidoPosicion = listado.get(posicion).toString();
        System.out.println(contenidoPosicion);
        return  contenidoPosicion;
    }

}
