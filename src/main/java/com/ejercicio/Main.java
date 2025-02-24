package com.ejercicio;


import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("\n---\nPersonas\n---\n");

        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        inscripcionesPersonas.cargarDatos();
        List<Persona> listado = inscripcionesPersonas.getListado();
        System.out.println(listado.toString());

        System.out.println("\n---\nCursosProfesores\n---\n");

        CursosProfesores cursosProfesores = new CursosProfesores();
        cursosProfesores.cargarDatos();
        cursosProfesores.imprimirListado();

        System.out.println("\n---\nCursosInscritos\n---\n");

        CursosInscritos cursosInscritos = new CursosInscritos();
        cursosInscritos.cargarDatos();
        cursosInscritos.imprimirListado();

    }
}