package com.emergencias.detector;

import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;

public class DetectorEmergencia {
    private String tipoDeteccion;
    private int umbralSensibilidad;

    public DetectorEmergencia(String tipoDeteccion, int umbralSensibilidad) {
        this.tipoDeteccion = tipoDeteccion;
        this.umbralSensibilidad = umbralSensibilidad;
    }

    public EventoEmergencia detectarEmergencia(int nivelIntroducido,
                                               String ubicacion,
                                               FichaMedica fichaMedica,
                                               boolean confirmada) {

        validarNivelEnRango(nivelIntroducido, 0, 10);
        validarTextoNoVacio(ubicacion, "La ubicación no puede estar vacía.");
        validarFichaMedica(fichaMedica);

        if (nivelIntroducido < umbralSensibilidad || !confirmada) {
            return null;
        }

        String gravedad = calcularGravedad(nivelIntroducido);

        return new EventoEmergencia(
                tipoDeteccion,
                ubicacion.trim(),
                gravedad,
                fichaMedica
        );
    }

    private void validarNivelEnRango(int valor, int min, int max) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException("El nivel de emergencia debe estar entre " + min + " y " + max + ".");
        }
    }

    private void validarTextoNoVacio(String texto, String mensajeError) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    private void validarFichaMedica(FichaMedica fichaMedica) {
        if (fichaMedica == null) {
            throw new IllegalArgumentException("La ficha médica no puede ser nula.");
        }

        validarTextoNoVacio(fichaMedica.getNombre(), "El nombre del paciente no puede estar vacío.");
        validarTextoNoVacio(fichaMedica.getTelefono(), "El teléfono no puede estar vacío.");
        validarTextoNoVacio(fichaMedica.getGrupoSanguineo(), "El grupo sanguíneo no puede estar vacío.");
        validarTextoNoVacio(fichaMedica.getAlergias(), "Las alergias no pueden estar vacías.");
        validarTextoNoVacio(fichaMedica.getMedicacion(), "La medicación no puede estar vacía.");
        validarTextoNoVacio(fichaMedica.getContactoEmergencia(), "El contacto de emergencia no puede estar vacío.");
    }

    private String calcularGravedad(int nivelIntroducido) {
        if (nivelIntroducido >= 8) {
            return "grave";
        } else if (nivelIntroducido >= 5) {
            return "moderada";
        } else {
            return "leve";
        }
    }
}