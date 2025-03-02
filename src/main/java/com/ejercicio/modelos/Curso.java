package com.ejercicio.modelos;

public class Curso{
    Integer ID;
    Boolean activo;
    Programa programa;
    String nombre;

    public Curso(Integer ID, Boolean activo, Programa programa, String nombre) {
        this.ID = ID;
        this.activo = activo;
        this.programa = programa;
        this.nombre = nombre;
    }

    public Curso(){
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return
                "IDCurso=" + ID +
                "\nnombreCurso=" + nombre +
                "\nactivo=" + activo +
                "\nprograma{\n" + programa.toString() + "\n}" ;
    }
}
