package com.emergencias.inventario;

public class InventarioVehiculo {

    public String obtenerRecursosDisponibles(String tipoEmergencia, String gravedad) {
        String tipoNormalizado = tipoEmergencia.trim().toLowerCase();
        StringBuilder recursos = new StringBuilder();

        recursos.append("=== INVENTARIO DEL VEHÍCULO ===\n");
        recursos.append("Gravedad detectada: ").append(gravedad).append("\n");
        recursos.append("Recursos base disponibles:\n");
        recursos.append("- Botiquín\n");
        recursos.append("- Manta térmica\n");

        if (tipoNormalizado.equals("parada cardiaca") || tipoNormalizado.equals("parada cardíaca")) {
            recursos.append("- Desfibrilador: DESBLOQUEADO\n");
        } else {
            recursos.append("- Desfibrilador: disponible solo para emergencia cardiaca\n");
        }

        if (gravedad.equalsIgnoreCase("grave")) {
            recursos.append("- Señalización de emergencia recomendada\n");
            recursos.append("- Prioridad máxima de asistencia\n");
        }

        recursos.append("===============================");

        return recursos.toString();
    }
}