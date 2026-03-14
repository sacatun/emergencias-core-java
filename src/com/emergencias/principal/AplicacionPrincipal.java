package com.emergencias.principal;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.asistencia.ProtocoloPrimerosAuxilios;
import com.emergencias.controlador.GestorEmergencias;
import com.emergencias.detector.DetectorEmergencia;
import com.emergencias.inventario.InventarioVehiculo;
import com.emergencias.recursos.LocalizadorRecursos;

public class AplicacionPrincipal {

    public static void main(String[] args) {

        // --- Inicialización de componentes del sistema ---

        DetectorEmergencia detector = new DetectorEmergencia("Accidente de tráfico", 5);

        EnviadorAlertas enviador = new EnviadorAlertas(
                "112",
                "alertas.txt",
                "SMS"
        );

        ProtocoloPrimerosAuxilios protocolo = new ProtocoloPrimerosAuxilios();

        LocalizadorRecursos localizador = new LocalizadorRecursos();

        InventarioVehiculo inventario = new InventarioVehiculo();


        // --- Gestor principal del sistema ---
        GestorEmergencias gestor = new GestorEmergencias(
                detector,
                enviador,
                protocolo,
                localizador,
                inventario
        );


        // --- Inicio del sistema ---
        gestor.iniciarSistema();
    }
}