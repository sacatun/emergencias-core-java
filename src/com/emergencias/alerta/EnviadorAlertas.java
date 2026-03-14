package com.emergencias.alerta;

import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class EnviadorAlertas {
    private String destino;
    private String rutaArchivoLog;
    private String metodoEnvio;

    public EnviadorAlertas(String destino, String rutaArchivoLog, String metodoEnvio) {
        this.destino = destino;
        this.rutaArchivoLog = rutaArchivoLog;
        this.metodoEnvio = metodoEnvio;
    }

    public void enviarAlerta(EventoEmergencia evento) {
        System.out.println("=== Enviando alerta ===");
        System.out.println("Destino: " + destino + " | Método: " + metodoEnvio);
        System.out.println("Tipo de emergencia: " + evento.getTipoEmergencia());
        System.out.println("Ubicación: " + evento.getUbicacion());
        System.out.println("Gravedad: " + evento.getGravedad());

        FichaMedica ficha = evento.getFichaMedica();
        System.out.println("Paciente identificado: " + ficha.getNombre());
        System.out.println("Teléfono: " + ficha.getTelefono());
        System.out.println("Grupo sanguíneo: " + ficha.getGrupoSanguineo());
        System.out.println("Alergias: " + ficha.getAlergias());
        System.out.println("Medicación: " + ficha.getMedicacion());
        System.out.println("Contacto de emergencia: " + ficha.getContactoEmergencia());
        System.out.println("========================");

        try (FileWriter writer = new FileWriter(rutaArchivoLog, true)) {
            writer.write("=== ALERTA ===" + System.lineSeparator());
            writer.write("Fecha/hora: " + LocalDateTime.now() + System.lineSeparator());
            writer.write("Destino: " + destino + " | Método: " + metodoEnvio + System.lineSeparator());
            writer.write("Tipo de emergencia: " + evento.getTipoEmergencia() + System.lineSeparator());
            writer.write("Ubicación: " + evento.getUbicacion() + System.lineSeparator());
            writer.write("Gravedad: " + evento.getGravedad() + System.lineSeparator());
            writer.write("Paciente identificado: " + ficha.getNombre() + System.lineSeparator());
            writer.write("Teléfono: " + ficha.getTelefono() + System.lineSeparator());
            writer.write("Grupo sanguíneo: " + ficha.getGrupoSanguineo() + System.lineSeparator());
            writer.write("Alergias: " + ficha.getAlergias() + System.lineSeparator());
            writer.write("Medicación: " + ficha.getMedicacion() + System.lineSeparator());
            writer.write("Contacto de emergencia: " + ficha.getContactoEmergencia() + System.lineSeparator());
            writer.write("------------------------" + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al guardar la alerta en el archivo: " + e.getMessage());
        }
    }
}