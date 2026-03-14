package com.emergencias.controlador;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.asistencia.ProtocoloPrimerosAuxilios;
import com.emergencias.detector.DetectorEmergencia;
import com.emergencias.inventario.InventarioVehiculo;
import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.recursos.LocalizadorRecursos;

import java.util.Scanner;

public class GestorEmergencias {
    private final DetectorEmergencia detectorEmergencia;
    private final EnviadorAlertas enviadorAlertas;
    private final ProtocoloPrimerosAuxilios protocoloPrimerosAuxilios;
    private final LocalizadorRecursos localizadorRecursos;
    private final InventarioVehiculo inventarioVehiculo;
    private final Scanner sc = new Scanner(System.in);

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

    public void iniciarSistema() {
        EventoEmergencia evento = detectorEmergencia.detectarEmergencia();

        if (evento == null) {
            System.out.println("No se ha detectado emergencia.");
            return;
        }

        System.out.println("¡¡¡ALERTA CONFIRMADA!!!");
        System.out.println("Paciente identificado: " + evento.getFichaMedica().getNombre());
        System.out.println("Gravedad detectada: " + evento.getGravedad());
        System.out.println();

        enviadorAlertas.enviarAlerta(evento);

        String tipoAsistencia = leerTipoAsistencia();
        protocoloPrimerosAuxilios.mostrarProtocolo(tipoAsistencia);
        inventarioVehiculo.mostrarRecursosDisponibles(tipoAsistencia, evento.getGravedad());

        String municipio = leerTextoNoVacio("Introduce el municipio para buscar centros de salud cercanos: ");
        localizadorRecursos.mostrarCentrosPorMunicipio(municipio);
    }

    private String leerTipoAsistencia() {
        while (true) {
            System.out.print("Selecciona tipo de asistencia (herida / parada cardiaca / inconsciencia): ");
            String tipo = sc.nextLine().trim().toLowerCase();

            if (tipo.equals("herida") || tipo.equals("parada cardiaca")
                    || tipo.equals("parada cardíaca") || tipo.equals("inconsciencia")) {
                return tipo;
            }

            System.out.println("Tipo no válido. Introduce: herida, parada cardiaca o inconsciencia.");
        }
    }

    private String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Este campo no puede estar vacío.");
        }
    }
}