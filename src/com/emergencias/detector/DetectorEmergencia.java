package com.emergencias.detector;

import com.emergencias.modelo.DatosUsuario;
import com.emergencias.modelo.EventoEmergencia;
import com.emergencias.modelo.TipoEmergencia;

import java.util.Scanner;

public class DetectorEmergencia {
    // MEJORA: sc era package-private (sin modificador) y se creaba internamente con new Scanner(System.in).
    // Ahora es private y se recibe desde fuera para que haya un único Scanner en toda la aplicación.
    // Cerrar un Scanner sobre System.in cierra el propio System.in, por eso se gestiona desde el main.
    // MEJORA: eliminado tipoDeteccion del constructor, ahora el usuario lo elige en tiempo de ejecución
    private Scanner sc;
    private int umbralSensibilidad;

    public DetectorEmergencia(Scanner sc, int umbralSensibilidad) {
        this.sc = sc;
        this.umbralSensibilidad = umbralSensibilidad;
    }   

    public EventoEmergencia detectarEmergencia() {
        int nivelIntroducido = leerEnteroEnRango("Introduce un nivel de emergencia (0-10): ", 0, 10);

        if (nivelIntroducido >= umbralSensibilidad) {
            // MEJORA: el tipo de emergencia lo elige el usuario entre los valores del enum TipoEmergencia
            TipoEmergencia tipo = elegirTipoEmergencia();

            System.out.print("Introduce ubicación: ");
            String ubicacion = sc.nextLine();

            System.out.print("Introduce nombre del usuario: ");
            String nombre = sc.nextLine();

            System.out.print("Introduce teléfono: ");
            String telefono = sc.nextLine();

            System.out.print("Introduce email: ");
            String email = sc.nextLine();

            DatosUsuario datos = new DatosUsuario(nombre, telefono, email);

            // MEJORA: se pasa nivelIntroducido al evento para que no se pierda
            EventoEmergencia evento = new EventoEmergencia(tipo, ubicacion, datos, nivelIntroducido);

            // FEATURE: confirmación para evitar falsos positivos
            if (!confirmarEmergencia()) {
                System.out.println("Emergencia cancelada.");
                return null;
            }

            return evento;
        }
        return null;
    }

    // MEJORA: muestra los tipos disponibles del enum y devuelve el elegido por el usuario
    private TipoEmergencia elegirTipoEmergencia() {
        TipoEmergencia[] tipos = TipoEmergencia.values();
        System.out.println("Selecciona el tipo de emergencia:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println("  " + (i + 1) + ". " + tipos[i].getDescripcion());
        }
        int opcion = leerEnteroEnRango("Opción: ", 1, tipos.length);
        return tipos[opcion - 1];
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