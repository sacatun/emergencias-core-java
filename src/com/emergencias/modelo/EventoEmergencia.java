package com.emergencias.modelo;

public class EventoEmergencia {
    private String tipoEmergencia;
    private String ubicacion;
    private String gravedad;
    private FichaMedica fichaMedica;

    public EventoEmergencia(String tipoEmergencia, String ubicacion, String gravedad, FichaMedica fichaMedica) {
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        this.gravedad = gravedad;
        this.fichaMedica = fichaMedica;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getGravedad() {
        return gravedad;
    }

    public FichaMedica getFichaMedica() {
        return fichaMedica;
    }
}