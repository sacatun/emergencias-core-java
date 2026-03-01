package com.emergencias.detector;

import com.emergencias.modelo.DatosUsuario;
import com.emergencias.modelo.EventoEmergencia;

import java.util.Scanner;

public class DetectorEmergencia {
    // MEJORA: sc era package-private (sin modificador) y se creaba internamente con new Scanner(System.in).
    // Ahora es private y se recibe desde fuera para que haya un único Scanner en toda la aplicación.
    // Cerrar un Scanner sobre System.in cierra el propio System.in, por eso se gestiona desde el main.
    private Scanner sc;
    private String tipoDeteccion;
    private int umbralSensibilidad;

    public DetectorEmergencia(Scanner sc, String tipoDeteccion, int umbralSensibilidad) {
        this.sc = sc;
        this.tipoDeteccion = tipoDeteccion;
        this.umbralSensibilidad = umbralSensibilidad;
    }

    public EventoEmergencia detectarEmergencia(
    ) {
        int nivelIntroducido = leerEnteroEnRango("Introduce un nivel de emergencia (0-10): ", 0, 10);

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

    private int leerEnteroEnRango(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();

            try {
                int valor = Integer.parseInt(linea);
                if (valor < min || valor > max) {
                    System.out.println("Valor fuera de rango. Debe estar entre " + min + " y " + max + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número.");
            }
        }
    }


}