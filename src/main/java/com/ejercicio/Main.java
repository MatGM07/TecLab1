package com.ejercicio;


import java.util.Date;
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
        Date fecha = new Date(1995,3,12);
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
        List<String> listado = cursosProfesores.toStringList();
        System.out.println(listado);
    }
}