package com.emergencias.detector;

import com.emergencias.modelo.DatosUsuario;
import com.emergencias.modelo.EventoEmergencia;

import java.util.Scanner;

public class DetectorEmergencia {
    Scanner sc = new Scanner(System.in);
    private String tipoDeteccion;
    private int umbralSensibilidad;

    public DetectorEmergencia(String tipoDeteccion, int umbralSensibilidad) {
        this.tipoDeteccion = tipoDeteccion;
        this.umbralSensibilidad = umbralSensibilidad;
    }

    public EventoEmergencia detectarEmergencia(
    ) {
        System.out.print("Introduce un nivel de emergencia (0–10): ");
        int nivelIntroducido = sc.nextInt();
        sc.nextLine(); // Limpiamos buffer

        if (nivelIntroducido >= umbralSensibilidad) {
            System.out.print("Introduce ubicación: ");
            String ubicacion = sc.nextLine();

            System.out.print("Introduce nombre del usuario: ");
            String nombre = sc.nextLine();

            System.out.print("Introduce teléfono: ");
            String telefono = sc.nextLine();

            System.out.print("Introduce email: ");
            String email = sc.nextLine();

            DatosUsuario datos = new DatosUsuario(nombre, telefono, email);

            EventoEmergencia evento = new EventoEmergencia(tipoDeteccion, ubicacion, datos);

            // FEATURE: confirmación para evitar falsos positivos
            if (!confirmarEmergencia()) {
                System.out.println("Emergencia cancelada.");
                return null;
            }

            return evento;
        }
        return null;
    }

    private boolean confirmarEmergencia() {
        System.out.print("¿Confirmas la emergencia? (S/N): ");
        String respuesta = sc.nextLine().trim();
        return respuesta.equalsIgnoreCase("S");
    }

}