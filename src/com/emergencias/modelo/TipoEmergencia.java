package com.emergencias.modelo;

// MEJORA: el tipo de emergencia era un String hardcodeado ("Incendio") en AplicacionPrincipal.
// Se convierte a enum para centralizar los tipos válidos y permitir al usuario elegir en tiempo de ejecución.
public enum TipoEmergencia {
    INCENDIO("Incendio"),
    INUNDACION("Inundación"),
    ACCIDENTE("Accidente de tráfico"),
    MEDICA("Emergencia médica");

    private final String descripcion;

    TipoEmergencia(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
