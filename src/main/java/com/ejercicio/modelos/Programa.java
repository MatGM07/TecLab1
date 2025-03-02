package com.ejercicio.modelos;

import java.sql.Date;

public class Programa {
    Integer ID;
    String nombre;
    Double duracion;
    Date registro;
    Facultad facultad;

    public Programa(Integer ID, String nombre, Double duracion, Date registro, Facultad facultad) {
        this.ID = ID;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public Programa(Integer ID, String nombre, Double duracion, Date registro, Integer facultad_id) {
        this.ID = ID;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = new Facultad(facultad_id,null,-1);
    }

    public Programa(){

    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
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

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
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
        return
                "IDPrograma=" + ID +
                "\nnombrePrograma=" + nombre +
                "\nduracion=" + duracion +
                "\nregistro=" + registro +
                "\nfacultad{\n" + facultad.toString() + "\n}";
    }
}
