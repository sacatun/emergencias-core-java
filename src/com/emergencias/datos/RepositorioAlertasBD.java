package com.emergencias.datos;

import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;
import com.emergencias.modelo.AlertaRegistrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAlertasBD {
    private final ConexionBD conexionBD;

    public RepositorioAlertasBD() {
        this(new ConexionBD());
    }

    public RepositorioAlertasBD(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public void guardar(EventoEmergencia evento, String destino, String metodoEnvio, LocalDateTime fechaHora)
            throws SQLException {

        String sql = "INSERT INTO alertas_emergencia (" +
                "fecha_hora, destino, metodo_envio, tipo_emergencia, ubicacion, gravedad, " +
                "paciente_nombre, paciente_telefono, grupo_sanguineo, alergias, medicacion, contacto_emergencia" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        FichaMedica ficha = evento.getFichaMedica();

        try (Connection conexion = conexionBD.abrir();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(fechaHora));
            ps.setString(2, destino);
            ps.setString(3, metodoEnvio);
            ps.setString(4, evento.getTipoEmergencia());
            ps.setString(5, evento.getUbicacion());
            ps.setString(6, evento.getGravedad());
            ps.setString(7, ficha.getNombre());
            ps.setString(8, ficha.getTelefono());
            ps.setString(9, ficha.getGrupoSanguineo());
            ps.setString(10, ficha.getAlergias());
            ps.setString(11, ficha.getMedicacion());
            ps.setString(12, ficha.getContactoEmergencia());
            ps.executeUpdate();
        }
    }

    public List<AlertaRegistrada> listarUltimas(int limite) throws SQLException {
        String sql = "SELECT id, fecha_hora, tipo_emergencia, ubicacion, gravedad, " +
                "paciente_nombre, metodo_envio " +
                "FROM alertas_emergencia " +
                "ORDER BY fecha_hora DESC, id DESC " +
                "LIMIT ?";

        List<AlertaRegistrada> alertas = new ArrayList<>();

        try (Connection conexion = conexionBD.abrir();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, limite);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alertas.add(new AlertaRegistrada(
                            rs.getInt("id"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getString("tipo_emergencia"),
                            rs.getString("ubicacion"),
                            rs.getString("gravedad"),
                            rs.getString("paciente_nombre"),
                            rs.getString("metodo_envio")
                    ));
                }
            }
        }

        return alertas;
    }
}
