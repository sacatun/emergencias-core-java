package com.emergencias.modelo;

import java.time.LocalDateTime;

public class AlertaRegistrada {
    private int id;
    private LocalDateTime fechaHora;
    private String tipoEmergencia;
    private String ubicacion;
    private String gravedad;
    private String pacienteNombre;
    private String metodoEnvio;

    public AlertaRegistrada(int id,
                            LocalDateTime fechaHora,
                            String tipoEmergencia,
                            String ubicacion,
                            String gravedad,
                            String pacienteNombre,
                            String metodoEnvio) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        this.gravedad = gravedad;
        this.pacienteNombre = pacienteNombre;
        this.metodoEnvio = metodoEnvio;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
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

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public String getMetodoEnvio() {
        return metodoEnvio;
    }
}
