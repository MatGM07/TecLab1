package com.ejercicio;

public class Estudiante extends Persona {
    Double codigo;
    Programa programa;
    Boolean activo;
    Double promedio;

    public Estudiante(Integer ID, String nombre, String apellidos, String email, Double codigo, Programa programa, Boolean activo, Double promedio) {
        super(ID, nombre, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public Estudiante(Double codigo, Programa programa, Boolean activo, Double promedio) {
        codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public Estudiante(){
    }

    public Double getCodigo() {
        return codigo;
    }

    public void setCodigo(Double codigo) {
        codigo = codigo;
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
        return  "IDEstudiante=" + this.getID()+
                "\nnombreEstudiante=" + this.getNombre() +
                "\napellidosEstudiante=" + this.getApellidos() +
                "\nemailEstudiante=" + this.getEmail() +
                "\nCodigoEstudiante=" + codigo +
                "\nactivoEstudiante=" + activo +
                "\npromedioEstudiante=" + promedio;
    }
}
