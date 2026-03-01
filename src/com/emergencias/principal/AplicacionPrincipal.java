package com.emergencias.principal;

import com.emergencias.alerta.EnviadorAlertas;
import com.emergencias.controlador.GestorEmergencias;
import com.emergencias.detector.DetectorEmergencia;

import com.emergencias.datos.CargadorCentrosSalud;
import com.emergencias.modelo.CentroSalud;

import java.util.ArrayList;
import java.util.Scanner;

public class AplicacionPrincipal {
    public static void main(String[] args) {

        // --- Carga de datos externos (JSON) ---
        ArrayList<CentroSalud> centros;
        try {
            centros = CargadorCentrosSalud.cargar();
            System.out.println("Centros de salud cargados desde JSON: " + centros.size());
            System.out.println("Ejemplo (primeros 3):");
            for (int i = 0; i < Math.min(3, centros.size()); i++) {
                System.out.println(" - " + centros.get(i));
            }
            long centrosMurcia = centros.stream()
                    .filter(c -> "Murcia".equalsIgnoreCase(c.getMunicipio()))
                    .count();
            System.out.println("Centros en el municipio 'Murcia': " + centrosMurcia);
            System.out.println("--------------------------------------");
        } catch (RuntimeException ex) {
            System.out.println("No se pudieron cargar los centros desde JSON: " + ex.getMessage());
            System.out.println("--------------------------------------");
            centros = new ArrayList<>();
        }

        // MEJORA: Scanner único para toda la aplicación, se pasa como parámetro en lugar de
        // que cada clase cree el suyo propio sobre System.in (lo que causaría múltiples instancias)
        Scanner sc = new Scanner(System.in);

        // Creamos los objetos con los parámetros de cada clase
        // MEJORA: eliminado "Incendio" hardcodeado, ahora el usuario elige el tipo en tiempo de ejecución
        DetectorEmergencia detector = new DetectorEmergencia(sc, 5);
        // MEJORA: log movido a logs/alertas.log para separar archivos de log del código fuente
        EnviadorAlertas enviador = new EnviadorAlertas("112", "logs/alertas.log", "SMS");

        // Creamos el objeto gestor con lo ya creado: detector y enviador
        GestorEmergencias gestor = new GestorEmergencias(detector, enviador);

        gestor.iniciarSistema();
    }
}
