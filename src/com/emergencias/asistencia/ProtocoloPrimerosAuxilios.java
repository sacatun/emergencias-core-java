package com.emergencias.asistencia;

public class ProtocoloPrimerosAuxilios {

    public String obtenerProtocolo(String tipoEmergencia) {
        String tipoNormalizado = tipoEmergencia.trim().toLowerCase();
        StringBuilder protocolo = new StringBuilder();

        protocolo.append("=== PROTOCOLO DE PRIMEROS AUXILIOS ===\n");

        switch (tipoNormalizado) {
            case "herida":
                protocolo.append("1. Mantén la calma y protege la zona.\n");
                protocolo.append("2. Ponte guantes si están disponibles.\n");
                protocolo.append("3. Presiona la herida con una gasa o paño limpio.\n");
                protocolo.append("4. Si sangra mucho, no retires el primer apósito; añade más encima.\n");
                protocolo.append("5. Espera a los servicios de emergencia.\n");
                break;

            case "parada cardiaca":
            case "parada cardíaca":
                protocolo.append("1. Comprueba si la persona responde.\n");
                protocolo.append("2. Comprueba si respira con normalidad.\n");
                protocolo.append("3. Llama o confirma aviso al 112.\n");
                protocolo.append("4. Inicia compresiones torácicas: 100-120 por minuto.\n");
                protocolo.append("5. Usa desfibrilador si está disponible.\n");
                break;

            case "inconsciencia":
                protocolo.append("1. Comprueba respuesta verbal y física.\n");
                protocolo.append("2. Comprueba respiración.\n");
                protocolo.append("3. Si respira, colócala en posición lateral de seguridad.\n");
                protocolo.append("4. Si no respira, inicia RCP.\n");
                protocolo.append("5. Mantén la vigilancia hasta que llegue ayuda.\n");
                break;

            default:
                protocolo.append("1. Mantén la calma.\n");
                protocolo.append("2. Asegura la zona.\n");
                protocolo.append("3. Comprueba el estado de la víctima.\n");
                protocolo.append("4. Sigue las indicaciones del 112.\n");
                protocolo.append("5. Espera a los servicios de emergencia.\n");
                break;
        }

        protocolo.append("======================================");

        return protocolo.toString();
    }
}