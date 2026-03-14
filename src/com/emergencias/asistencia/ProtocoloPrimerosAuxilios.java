package com.emergencias.asistencia;

public class ProtocoloPrimerosAuxilios {

    public void mostrarProtocolo(String tipoEmergencia) {
        String tipoNormalizado = tipoEmergencia.trim().toLowerCase();

        System.out.println();
        System.out.println("=== PROTOCOLO DE PRIMEROS AUXILIOS ===");

        switch (tipoNormalizado) {
            case "herida":
                System.out.println("1. Mantén la calma y protege la zona.");
                System.out.println("2. Ponte guantes si están disponibles.");
                System.out.println("3. Presiona la herida con una gasa o paño limpio.");
                System.out.println("4. Si sangra mucho, no retires el primer apósito; añade más encima.");
                System.out.println("5. Espera a los servicios de emergencia.");
                break;

            case "parada cardiaca":
            case "parada cardíaca":
                System.out.println("1. Comprueba si la persona responde.");
                System.out.println("2. Comprueba si respira con normalidad.");
                System.out.println("3. Llama o confirma aviso al 112.");
                System.out.println("4. Inicia compresiones torácicas: 100-120 por minuto.");
                System.out.println("5. Usa desfibrilador si está disponible.");
                break;

            case "inconsciencia":
                System.out.println("1. Comprueba respuesta verbal y física.");
                System.out.println("2. Comprueba respiración.");
                System.out.println("3. Si respira, colócala en posición lateral de seguridad.");
                System.out.println("4. Si no respira, inicia RCP.");
                System.out.println("5. Mantén la vigilancia hasta que llegue ayuda.");
                break;

            default:
                System.out.println("1. Mantén la calma.");
                System.out.println("2. Asegura la zona.");
                System.out.println("3. Comprueba el estado de la víctima.");
                System.out.println("4. Sigue las indicaciones del 112.");
                System.out.println("5. Espera a los servicios de emergencia.");
                break;
        }

        System.out.println("======================================");
        System.out.println();
    }
}