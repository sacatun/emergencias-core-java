package com.emergencias.modelo;

public class EventoEmergencia {
    private String tipoEmergencia;
    private String ubicacion;
    private DatosUsuario datosUsuario;
    // MEJORA: el nivel introducido por el usuario se perdía al crear el evento.
    // Ahora se almacena para poder mostrarlo en consola y registrarlo en el log.
    private int nivelEmergencia;

    public EventoEmergencia(String tipoEmergencia, String ubicacion, DatosUsuario datosUsuario, int nivelEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        this.datosUsuario = datosUsuario;
        this.nivelEmergencia = nivelEmergencia;
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

    public int getNivelEmergencia() {
        return nivelEmergencia;
    }
}
