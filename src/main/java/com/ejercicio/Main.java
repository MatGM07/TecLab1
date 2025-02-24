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
        p.setID(3.4);
        p.setNombre("eofddkeokf");

        Curso c = new Curso();
        c.setPrograma(p);
        c.setNombre("dfdsf");
        c.setID(23);
        c.setActivo(true);

        Estudiante e = new Estudiante();
        e.setEmail("dsfff");
        e.setPromedio(2.3);
        e.setNombre("dffsdfg");
        e.setApellidos("fdofg");
        e.setID(6.4);
        e.setPrograma(p);
        e.setActivo(true);
        e.setCodigo(6.7);

        Inscripción inscripción = new Inscripción();
        inscripción.setAño(2024);
        inscripción.setCurso(c);
        inscripción.setSemestre(5);
        inscripción.setEstudiante(e);

        CursosInscritos cursosInscritos = new CursosInscritos();

        cursosInscritos.guardarInformacion(inscripción);


   */
        CursosInscritos cursosInscritos = new CursosInscritos();
        cursosInscritos.cargarDatos();
        System.out.println(cursosInscritos.toStringList());

/*
        CursosProfesores cursosProfesores = new CursosProfesores();
        cursosProfesores.cargarDatos();

        System.out.println(cursosProfesores.cantidadActual());
*/

    }
}