package com.ejercicio;

public class Programa {
    Double ID;
    String nombre;
    Double duracion;
    String registro;
    Facultad facultad;

    public Programa(Double ID, String nombre, Double duracion, String registro, Facultad facultad) {
        this.ID = ID;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public Programa(){

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

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", registro='" + registro + '\'' +
                ", facultad=" + facultad +
                '}';
    }
}
