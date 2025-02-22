package com.ejercicio;

public class Inscripción {
    Curso curso;
    Integer año;
    Integer semestre;
    Estudiante estudiante;

    public Inscripción(Curso curso, Integer año, Integer semestre, Estudiante estudiante) {
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public Inscripción(){
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return
                "año=" + año +
                "\nsemestre=" + semestre +
                "\ncurso{\n" + curso.toString() + "\n}"+
                "\nestudiante{\n" + estudiante.toString() + "\n}" ;
    }
}
