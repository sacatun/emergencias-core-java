package com.emergencias.modelo;

public class EventoEmergencia {
    private String tipoEmergencia;
    private String ubicacion;
    private DatosUsuario datosUsuario;


    public EventoEmergencia(String tipoEmergencia, String ubicacion, DatosUsuario datosUsuario) {
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        this.datosUsuario = datosUsuario;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public DatosUsuario getDatosUsuario() {
        return datosUsuario;
    }
}
