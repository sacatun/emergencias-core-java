package com.emergencias.alerta;

import com.emergencias.datos.RepositorioAlertasBD;
import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EnviadorAlertas {
    private String destino;
    private String rutaArchivoLog;
    private String metodoEnvio;
    private RepositorioAlertasBD repositorioAlertasBD;

    public EnviadorAlertas(String destino, String rutaArchivoLog, String metodoEnvio) {
        this.destino = destino;
        this.rutaArchivoLog = rutaArchivoLog;
        this.metodoEnvio = metodoEnvio;
        this.repositorioAlertasBD = new RepositorioAlertasBD();
    }

    public String generarResumenAlerta(EventoEmergencia evento) {
        FichaMedica ficha = evento.getFichaMedica();

        return "=== ENVIANDO ALERTA ===\n" +
                "Destino: " + destino + " | Método: " + metodoEnvio + "\n" +
                "Tipo de emergencia: " + evento.getTipoEmergencia() + "\n" +
                "Ubicación: " + evento.getUbicacion() + "\n" +
                "Gravedad: " + evento.getGravedad() + "\n" +
                "Paciente identificado: " + ficha.getNombre() + "\n" +
                "Teléfono: " + ficha.getTelefono() + "\n" +
                "Grupo sanguíneo: " + ficha.getGrupoSanguineo() + "\n" +
                "Alergias: " + ficha.getAlergias() + "\n" +
                "Medicación: " + ficha.getMedicacion() + "\n" +
                "Contacto de emergencia: " + ficha.getContactoEmergencia() + "\n" +
                "========================";
    }

    public void enviarAlerta(EventoEmergencia evento) {
        String resumen = generarResumenAlerta(evento);
        LocalDateTime fechaHora = LocalDateTime.now();

        try (FileWriter writer = new FileWriter(rutaArchivoLog, true)) {
            writer.write("=== ALERTA ===" + System.lineSeparator());
            writer.write("Fecha/hora: " + fechaHora + System.lineSeparator());
            writer.write(resumen + System.lineSeparator());
            writer.write("------------------------" + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la alerta en el archivo: " + e.getMessage(), e);
        }

        try {
            repositorioAlertasBD.guardar(evento, destino, metodoEnvio, fechaHora);
        } catch (SQLException e) {
            System.out.println("No se pudo guardar la alerta en la base de datos: " + e.getMessage());
        }
    }
}
