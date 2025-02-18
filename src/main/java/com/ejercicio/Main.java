package com.ejercicio;




public class Main {
    public static void main(String[] args) {
        InscripcionesPersonas a = new InscripcionesPersonas();
        Persona person = new Persona();
        person.setEmail("suat@gmail.com");
        person.setApellidos("lover");
        person.setNombre("suat");
        person.setID((double) 0);
        a.guardarInformacion(person);
        a.cargarDatos();




    }
}