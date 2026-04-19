package com.emergencias.controlador;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.asistencia.ProtocoloPrimerosAuxilios;
import com.emergencias.datos.RepositorioAlertasBD;
import com.emergencias.detector.DetectorEmergencia;
import com.emergencias.inventario.InventarioVehiculo;
import com.emergencias.modelo.AlertaRegistrada;
import com.emergencias.modelo.FichaMedica;
import com.emergencias.modelo.ResultadoEmergencia;
import com.emergencias.recursos.LocalizadorRecursos;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ControladorVistaEmergencias {

    @FXML
    private TextField campoUbicacion;

    @FXML
    private TextField campoGravedad;

    @FXML
    private TextField campoMunicipio;

    @FXML
    private ComboBox<String> comboTipoAsistencia;

    @FXML
    private TextArea areaResultado;

    private GestorEmergencias gestor;
    private RepositorioAlertasBD repositorioAlertasBD;

    @FXML
    public void initialize() {
        repositorioAlertasBD = new RepositorioAlertasBD();

        gestor = new GestorEmergencias(
                new DetectorEmergencia("Accidente", 5),
                new EnviadorAlertas("112", "alertas.txt", "SMS"),
                new ProtocoloPrimerosAuxilios(),
                new LocalizadorRecursos(),
                new InventarioVehiculo()
        );

        comboTipoAsistencia.getItems().addAll(
                "herida",
                "parada cardiaca",
                "inconsciencia"
        );
    }

    @FXML
    public void procesarEmergencia() {
        try {
            String ubicacion = campoUbicacion.getText().trim();
            String textoGravedad = campoGravedad.getText().trim();
            String tipoAsistencia = comboTipoAsistencia.getValue();
            String municipio = campoMunicipio.getText().trim();

            if (ubicacion.isEmpty()) {
                areaResultado.setText("Error: la ubicación no puede estar vacía.");
                return;
            }

            if (textoGravedad.isEmpty()) {
                areaResultado.setText("Error: la gravedad no puede estar vacía.");
                return;
            }

            if (tipoAsistencia == null || tipoAsistencia.trim().isEmpty()) {
                areaResultado.setText("Error: debes seleccionar un tipo de asistencia.");
                return;
            }

            if (municipio.isEmpty()) {
                areaResultado.setText("Error: el municipio no puede estar vacío.");
                return;
            }

            int gravedad = Integer.parseInt(textoGravedad);

            FichaMedica ficha = new FichaMedica(
                    "Paciente",
                    "600000000",
                    "A+",
                    "Ninguna",
                    "Ninguna",
                    "Contacto"
            );

            ResultadoEmergencia resultado = gestor.procesarEmergencia(
                    gravedad,
                    ubicacion,
                    ficha,
                    true,
                    tipoAsistencia,
                    municipio
            );

            if (resultado == null) {
                areaResultado.setText("No se ha detectado una emergencia válida o no se ha confirmado.");
                return;
            }

            areaResultado.setText(
                    resultado.getResumenAlerta() + "\n\n" +
                            resultado.getProtocolo() + "\n\n" +
                            resultado.getInventario() + "\n\n" +
                            resultado.getTextoCentros()
            );

        } catch (NumberFormatException e) {
            areaResultado.setText("Error: la gravedad debe ser un número entero.");
        } catch (Exception e) {
            areaResultado.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void mostrarUltimasAlertas() {
        try {
            List<AlertaRegistrada> alertas = repositorioAlertasBD.listarUltimas(5);

            if (alertas.isEmpty()) {
                areaResultado.setText("No hay alertas guardadas en la base de datos.");
                return;
            }

            StringBuilder texto = new StringBuilder("Últimas alertas guardadas en MySQL:\n\n");
            for (AlertaRegistrada alerta : alertas) {
                texto.append("#").append(alerta.getId())
                        .append(" | ").append(alerta.getFechaHora())
                        .append(" | ").append(alerta.getTipoEmergencia())
                        .append(" | ").append(alerta.getUbicacion())
                        .append(" | Gravedad: ").append(alerta.getGravedad())
                        .append(" | Paciente: ").append(alerta.getPacienteNombre())
                        .append(" | Método: ").append(alerta.getMetodoEnvio())
                        .append("\n");
            }

            areaResultado.setText(texto.toString());
        } catch (SQLException e) {
            areaResultado.setText("No se pudieron consultar las alertas en la base de datos: " + e.getMessage());
        }
    }
}
