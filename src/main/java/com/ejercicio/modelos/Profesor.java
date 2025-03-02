package com.ejercicio.modelos;

public class Profesor extends Persona {
    String TipoContrato;

    public Profesor(Integer ID, String nombre, String apellidos, String email, String tipoContrato) {
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
        return  "IDProfesor=" + ID +
                "\nnombreProfesor=" + Nombre +
                "\napellidosProfesor=" + Apellidos +
                "\nemailProfesor=" + Email +
                "\nTipoContrato=" + TipoContrato;
    }
}
