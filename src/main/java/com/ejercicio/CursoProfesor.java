package com.ejercicio;

public class CursoProfesor {
    Profesor profesor;
    Integer año;
    Integer semestre;
    Curso curso;

    public CursoProfesor(Profesor profesor, Integer año, Integer semestre, Curso curso) {
        this.profesor = profesor;
        this.año = año;
        this.semestre = semestre;
        this.curso = curso;
    }

    public CursoProfesor(){

    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "CursoProfesor{" +
                "profesor=" + profesor +
                ", año=" + año +
                ", semestre=" + semestre +
                ", curso=" + curso +
                '}';
    }
}
