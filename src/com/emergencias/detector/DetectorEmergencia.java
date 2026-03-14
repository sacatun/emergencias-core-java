package com.emergencias.detector;

import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.FichaMedica;

import java.util.Scanner;

public class DetectorEmergencia {
    private final Scanner sc = new Scanner(System.in);
    private String tipoDeteccion;
    private int umbralSensibilidad;

    public DetectorEmergencia(String tipoDeteccion, int umbralSensibilidad) {
        this.tipoDeteccion = tipoDeteccion;
        this.umbralSensibilidad = umbralSensibilidad;
    }

    public EventoEmergencia detectarEmergencia() {
        int nivelIntroducido = leerEnteroEnRango("Introduce un nivel de emergencia (0-10): ", 0, 10);

        if (nivelIntroducido >= umbralSensibilidad) {
            String gravedad = calcularGravedad(nivelIntroducido);

            System.out.print("Introduce ubicación: ");
            String ubicacion = sc.nextLine();

            System.out.println("=== Identificación biomédica simulada ===");

            System.out.print("Introduce nombre del paciente: ");
            String nombre = sc.nextLine();

            System.out.print("Introduce teléfono: ");
            String telefono = sc.nextLine();

            System.out.print("Introduce grupo sanguíneo: ");
            String grupoSanguineo = sc.nextLine();

            System.out.print("Introduce alergias: ");
            String alergias = sc.nextLine();

            System.out.print("Introduce medicación: ");
            String medicacion = sc.nextLine();

            System.out.print("Introduce contacto de emergencia: ");
            String contactoEmergencia = sc.nextLine();

            FichaMedica fichaMedica = new FichaMedica(
                    nombre,
                    telefono,
                    grupoSanguineo,
                    alergias,
                    medicacion,
                    contactoEmergencia
            );

            EventoEmergencia evento = new EventoEmergencia(
                    tipoDeteccion,
                    ubicacion,
                    gravedad,
                    fichaMedica
            );

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

    private String calcularGravedad(int nivelIntroducido) {
        if (nivelIntroducido >= 8) {
            return "grave";
        } else if (nivelIntroducido >= 5) {
            return "moderada";
        } else {
            return "leve";
        }
    }
}