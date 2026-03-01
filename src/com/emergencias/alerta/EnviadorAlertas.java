package com.emergencias.alerta;

import com.emergencias.modelo.DatosUsuario;
import com.emergencias.modelo.EventoEmergencia;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class EnviadorAlertas {
    private String destino;
    private String rutaArchivolog;
    private String metodoEnvio;

    public EnviadorAlertas(String destino, String rutaArchivolog, String metodoEnvio) {
        this.destino = destino;
        this.rutaArchivolog = rutaArchivolog;
        this.metodoEnvio = metodoEnvio;
    }

    public void enviarAlerta(EventoEmergencia evento) {
        System.out.println("=== Enviando alerta ===");
        System.out.println("Destino: " + destino + " | Método: " + metodoEnvio);

        System.out.println("Tipo de emergencia: " + evento.getTipoEmergencia());
        // MEJORA: se muestra el nivel de emergencia, antes se perdía al crear el evento
        System.out.println("Nivel de emergencia: " + evento.getNivelEmergencia());
        System.out.println("Ubicacion: " + evento.getUbicacion());

        DatosUsuario usuario = evento.getDatosUsuario();
        System.out.println("Nombre usuario: " + usuario.getNombre());
        System.out.println("Teléfono: " + usuario.getTelefono());
        System.out.println("Email: " + usuario.getEmail());

        System.out.println("========================");

        // MEJORA: se especifica UTF-8 explícitamente para evitar corrupción de caracteres especiales
        // (tildes, ñ, etc.) en sistemas Windows donde el encoding por defecto es Windows-1252
        try (FileWriter writer = new FileWriter(rutaArchivolog, StandardCharsets.UTF_8, true)) {
            writer.write("=== ALERTA ===" + System.lineSeparator());
            writer.write("Fecha/hora: " + LocalDateTime.now() + System.lineSeparator());
            writer.write("Destino: " + destino + " | Método: " + metodoEnvio + System.lineSeparator());
            writer.write("Tipo de emergencia: " + evento.getTipoEmergencia() + System.lineSeparator());
            writer.write("Ubicación: " + evento.getUbicacion() + System.lineSeparator());

            writer.write("Nombre usuario: " + usuario.getNombre() + System.lineSeparator());
            writer.write("Teléfono: " + usuario.getTelefono() + System.lineSeparator());
            writer.write("Email: " + usuario.getEmail() + System.lineSeparator());
            writer.write("------------------------" + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al guardar la alerta en el archivo: " + e.getMessage());
        }

    }
}