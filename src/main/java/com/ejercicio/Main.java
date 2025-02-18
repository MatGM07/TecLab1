package com.ejercicio;




public class Main {
    public static void main(String[] args) {
        InscripcionesPersonas a = new InscripcionesPersonas();
        a.cargarDatos();
        System.out.println(a.getListado());

    }
}