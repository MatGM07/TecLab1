package com.ejercicio;




public class Main {
    public static void main(String[] args) {
        InscripcionesPersonas a = new InscripcionesPersonas();
        Persona person = new Persona();
        person.setEmail("sust@gmail.com");
        person.setApellidos("Stallman");
        person.setNombre("Richard");
        person.setID((double) 0);
        a.guardarInformacion(person);
        a.cargarDatos();
        System.out.println(a.getListado());

    }
}