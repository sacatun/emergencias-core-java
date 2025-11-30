package com.emergencias.modelo;

public class DatosUsuario {
    private String nombre;
    private String telefono;
    private String email;

    public DatosUsuario(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
}
