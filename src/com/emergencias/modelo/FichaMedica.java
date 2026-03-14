package com.emergencias.modelo;

public class FichaMedica {
    private String nombre;
    private String telefono;
    private String grupoSanguineo;
    private String alergias;
    private String medicacion;
    private String contactoEmergencia;

    public FichaMedica(String nombre, String telefono, String grupoSanguineo,
                       String alergias, String medicacion, String contactoEmergencia) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
        this.medicacion = medicacion;
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Teléfono: " + telefono +
                ", Grupo sanguíneo: " + grupoSanguineo +
                ", Alergias: " + alergias +
                ", Medicación: " + medicacion +
                ", Contacto de emergencia: " + contactoEmergencia;
    }
}