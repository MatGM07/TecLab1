package com.ejercicio;




public class Main {
    public static void main(String[] args) {
        InscripcionesPersonas a = new InscripcionesPersonas();
        Programa p1 = new Programa();
        Estudiante e1 = new Estudiante();
        e1.setActivo(true);
        e1.setCodigo(3.5);
        e1.setPrograma(p1);
        e1.setID(3.0);
        e1.setNombre("hola");
        e1.setApellidos("gkk");
        e1.setPromedio(4.5);
        e1.setEmail("refe");

        System.out.println(e1.toString());

    }
}