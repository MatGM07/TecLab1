package com.ejercicio;

public class Estudiante extends Persona {
    Double Codigo;
    Programa programa;
    Boolean activo;
    Double promedio;

    public Estudiante(Double ID, String nombre, String apellidos, String email, Double codigo, Programa programa, Boolean activo, Double promedio) {
        super(ID, nombre, apellidos, email);
        Codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public Estudiante(Double codigo, Programa programa, Boolean activo, Double promedio) {
        Codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public Estudiante(){
    }

    public Double getCodigo() {
        return Codigo;
    }

    public void setCodigo(Double codigo) {
        Codigo = codigo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "\nCodigo=" + Codigo +
                "\nactivo=" + activo +
                "\npromedio=" + promedio+
                "\nprograma{\n" + programa.toString() + "\n}"  ;
    }
}
