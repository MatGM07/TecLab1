package com.ejercicio;


import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
/*

        Persona d = new Persona();
        d.setNombre("sqs");
        d.setEmail("wdaefd");
        d.setApellidos("dsfsdf");
        d.setID(3.2);

        Facultad f = new Facultad();
        f.setDecano(d);
        f.setNombre("gjgjgjg");
        f.setID(23.3);

        Programa p = new Programa();
        p.setFacultad(f);
        String fechaStr = "2024-02-23";

        // Convertir a java.sql.Date
        java.sql.Date fecha = Date.valueOf(fechaStr);
        p.setRegistro(fecha);
        p.setDuracion(230.4);
        p.setNombre("eofddkeokf");

        Curso c = new Curso();
        c.setPrograma(p);
        c.setNombre("dfdsf");
        c.setID(23);
        c.setActivo(true);

        Profesor profe = new Profesor();
        profe.setEmail("defdejf");
        profe.setNombre("dffd");
        profe.setApellidos("fdefdsf");
        profe.setID(95.4);
        profe.setTipoContrato("jefe");

        CursoProfesor cursoProfesor = new CursoProfesor();
        cursoProfesor.setSemestre(4);
        cursoProfesor.setProfesor(profe);
        cursoProfesor.setAño(2025);
        cursoProfesor.setCurso(c);

        CursosProfesores cursosProfesores = new CursosProfesores();

        cursosProfesores.guardarInformacion(cursoProfesor);
*/

        CursosProfesores cursosProfesores = new CursosProfesores();
        cursosProfesores.cargarDatos();

        System.out.println(cursosProfesores.cantidadActual());


    }
}