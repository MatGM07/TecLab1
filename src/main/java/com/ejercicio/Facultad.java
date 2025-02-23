package com.ejercicio;

public class Facultad {
    Double ID;
    String nombre;
    Persona decano;

    public Facultad(Double ID, String nombre, Persona decano) {
        this.ID = ID;
        this.nombre = nombre;
        this.decano = decano;
    }

    public Facultad(){

    }

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona getDecano() {
        return decano;
    }

    public void setDecano(Persona decano) {
        this.decano = decano;
    }

    @Override
    public String toString() {
        return
                "IDFacultad=" + ID +
                "\nnombreFacultad=" + nombre +
                "\ndecano{\n" + decano.toString() + "\n}";
    }
}
