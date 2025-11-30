package com.emergencias.principal;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.controlador.GestorEmergencias;
import com.emergencias.detector.DetectorEmergencia;

public class AplicacionPrincipal {
    public static void main(String[] args) {

        // Creamos los objetos con los parámetros de cada clase
        DetectorEmergencia detector = new DetectorEmergencia("Incendio", 5);
        EnviadorAlertas enviador = new EnviadorAlertas("112", "alertas.txt", "SMS");

        // Creamos el objeto gestor con lo ya creado: detector y enviador
        GestorEmergencias gestor = new GestorEmergencias(detector, enviador);

        gestor.iniciarSistema();
    }
}
