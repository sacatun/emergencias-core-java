package com.emergencias.controlador;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.asistencia.ProtocoloPrimerosAuxilios;
import com.emergencias.detector.DetectorEmergencia;
import com.emergencias.inventario.InventarioVehiculo;
import com.emergencias.modelo.CentroSalud;
import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;
import com.emergencias.modelo.ResultadoEmergencia;
import com.emergencias.recursos.LocalizadorRecursos;

import java.util.List;

public class GestorEmergencias {
    private final DetectorEmergencia detectorEmergencia;
    private final EnviadorAlertas enviadorAlertas;
    private final ProtocoloPrimerosAuxilios protocoloPrimerosAuxilios;
    private final LocalizadorRecursos localizadorRecursos;
    private final InventarioVehiculo inventarioVehiculo;

    public GestorEmergencias(DetectorEmergencia detectorEmergencia,
                             EnviadorAlertas enviadorAlertas,
                             ProtocoloPrimerosAuxilios protocoloPrimerosAuxilios,
                             LocalizadorRecursos localizadorRecursos,
                             InventarioVehiculo inventarioVehiculo) {
        this.detectorEmergencia = detectorEmergencia;
        this.enviadorAlertas = enviadorAlertas;
        this.protocoloPrimerosAuxilios = protocoloPrimerosAuxilios;
        this.localizadorRecursos = localizadorRecursos;
        this.inventarioVehiculo = inventarioVehiculo;
    }

    public ResultadoEmergencia procesarEmergencia(int nivel,
                                                  String ubicacion,
                                                  FichaMedica fichaMedica,
                                                  boolean confirmada,
                                                  String tipoAsistencia,
                                                  String municipio) {

        EventoEmergencia evento = detectorEmergencia.detectarEmergencia(
                nivel,
                ubicacion,
                fichaMedica,
                confirmada
        );

        if (evento == null) {
            return null;
        }

        enviadorAlertas.enviarAlerta(evento);

        String resumenAlerta = enviadorAlertas.generarResumenAlerta(evento);
        String protocolo = protocoloPrimerosAuxilios.obtenerProtocolo(tipoAsistencia);
        String inventario = inventarioVehiculo.obtenerRecursosDisponibles(tipoAsistencia, evento.getGravedad());

        List<CentroSalud> centros = localizadorRecursos.obtenerCentrosPorMunicipio(municipio);
        String textoCentros = localizadorRecursos.formatearCentros(centros, municipio);

        return new ResultadoEmergencia(
                evento,
                resumenAlerta,
                protocolo,
                inventario,
                centros,
                textoCentros
        );
    }
}