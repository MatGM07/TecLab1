package com.ejercicio;

public class Profesor extends Persona {
    String TipoContrato;

    public Profesor(Double ID, String nombre, String apellidos, String email, String tipoContrato) {
        super(ID, nombre, apellidos, email);
        TipoContrato = tipoContrato;
    }

    public Profesor(){

    }

    public Profesor(String tipoContrato) {
        TipoContrato = tipoContrato;
    }

    public String getTipoContrato() {
        return TipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        TipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipoContrato=" + TipoContrato;
    }
}
