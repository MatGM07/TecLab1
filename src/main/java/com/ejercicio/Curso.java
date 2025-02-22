package com.ejercicio;

public class Curso{
    Integer ID;
    Boolean activo;
    Programa programa;

    public Curso(Integer ID, Boolean activo, Programa programa) {
        this.ID = ID;
        this.activo = activo;
        this.programa = programa;
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

    @Override
    public String toString() {
        return
                "ID=" + ID +
                "\nactivo=" + activo +
                "\nprograma{\n" + programa.toString() + "\n}" ;
    }
}
