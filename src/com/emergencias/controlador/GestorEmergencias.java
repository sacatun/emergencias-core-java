package com.emergencias.controlador;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.detector.DetectorEmergencia;
import com.emergencias.modelo.EventoEmergencia;

public class GestorEmergencias {
    private DetectorEmergencia detectorEmergencia;
    private EnviadorAlertas enviadorAlertas;

    public GestorEmergencias(DetectorEmergencia detectorEmergencia, EnviadorAlertas enviadorAlertas) {
        this.detectorEmergencia = detectorEmergencia;
        this.enviadorAlertas = enviadorAlertas;
    }

    public void iniciarSistema() {
        EventoEmergencia evento = detectorEmergencia.detectarEmergencia();
        if (evento == null) {
            System.out.println("No se ha detectado emergencia");
        } else {
            System.out.println("¡¡¡ALERTA!!!");
            enviadorAlertas.enviarAlerta(evento);
        }
    }
}
