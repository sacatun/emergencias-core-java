package com.emergencias.principal;

import com.emergencias.datos.RepositorioAlertasBD;
import com.emergencias.modelo.AlertaRegistrada;
import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Demostración rápida de operaciones JDBC con PreparedStatement sobre la tabla alertas_emergencia.
 *
 * Requisitos:
 * - Base de datos creada con sql/emergencias.sql
 * - Variables opcionales de entorno: EMERGENCIAS_DB_URL, EMERGENCIAS_DB_USER, EMERGENCIAS_DB_PASSWORD
 */
public class DemoUT12CRUD {

    public static void main(String[] args) {
        RepositorioAlertasBD repositorio = new RepositorioAlertasBD();

        FichaMedica ficha = new FichaMedica(
                "Paciente demo",
                "600111222",
                "O+",
                "Ninguna",
                "Paracetamol",
                "Familiar demo"
        );

        EventoEmergencia evento = new EventoEmergencia(
                "Caída",
                "Aula 2B",
                "7",
                ficha
        );

        try {
            // CREATE
            repositorio.guardar(evento, "112", "SMS", LocalDateTime.now());
            System.out.println("CREATE ✅ Alerta insertada.");

            // READ (lista)
            List<AlertaRegistrada> ultimas = repositorio.listarUltimas(1);
            if (ultimas.isEmpty()) {
                System.out.println("READ ⚠️ No se encontró ninguna alerta.");
                return;
            }

            AlertaRegistrada ultima = ultimas.get(0);
            System.out.println("READ ✅ Última alerta: #" + ultima.getId() + " - " + ultima.getTipoEmergencia());

            // READ (por id)
            AlertaRegistrada porId = repositorio.buscarPorId(ultima.getId());
            System.out.println("READ by ID ✅ Método envío actual: " + porId.getMetodoEnvio());

            // UPDATE
            boolean actualizada = repositorio.actualizarMetodoEnvio(ultima.getId(), "LLAMADA");
            System.out.println("UPDATE " + (actualizada ? "✅" : "⚠️") + " Cambio de método de envío.");

            // DELETE
            boolean eliminada = repositorio.eliminarPorId(ultima.getId());
            System.out.println("DELETE " + (eliminada ? "✅" : "⚠️") + " Eliminación de la alerta demo.");

        } catch (SQLException e) {
            System.out.println("Error JDBC: " + e.getMessage());
        }
    }
}
