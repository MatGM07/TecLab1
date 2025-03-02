package com.ejercicio.modelos;

public class Persona {
    Integer ID;
    String Nombre;
    String Apellidos;
    String Email;

    public Persona(Integer ID, String nombre, String apellidos, String email) {
        this.ID = ID;
        Nombre = nombre;
        Apellidos = apellidos;
        Email = email;
    }

    public Persona(){
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return
                "ID=" + ID +
                "\nNombre=" + Nombre +
                "\nApellidos=" + Apellidos +
                "\nEmail=" + Email;
    }
}
