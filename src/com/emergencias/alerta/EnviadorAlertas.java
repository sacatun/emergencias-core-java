package com.emergencias.alerta;

import com.emergencias.modelo.DatosUsuario;
import com.emergencias.modelo.EventoEmergencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnviadorAlertas {
    // MEJORA: formato legible en lugar del ISO 8601 por defecto (ej: 2026-03-01T14:01:56.248030500)
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

        // MEJORA: crea la carpeta logs/ automáticamente si no existe, para evitar error al escribir
        new File(rutaArchivolog).getParentFile().mkdirs();

        // MEJORA: se especifica UTF-8 explícitamente para evitar corrupción de caracteres especiales
        // (tildes, ñ, etc.) en sistemas Windows donde el encoding por defecto es Windows-1252
        try (FileWriter writer = new FileWriter(rutaArchivolog, StandardCharsets.UTF_8, true)) {
            writer.write("=== ALERTA ===" + System.lineSeparator());
            // MEJORA: formato legible "yyyy-MM-dd HH:mm:ss" en lugar del ISO 8601 con nanosegundos
            writer.write("Fecha/hora: " + LocalDateTime.now().format(FORMATO_FECHA) + System.lineSeparator());
            writer.write("Destino: " + destino + " | Método: " + metodoEnvio + System.lineSeparator());
            writer.write("Tipo de emergencia: " + evento.getTipoEmergencia() + System.lineSeparator());
            // MEJORA: se registra el nivel de emergencia en el log
            writer.write("Nivel de emergencia: " + evento.getNivelEmergencia() + System.lineSeparator());
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