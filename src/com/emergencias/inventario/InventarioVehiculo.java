package com.emergencias.inventario;

public class InventarioVehiculo {

    public void mostrarRecursosDisponibles(String tipoEmergencia, String gravedad) {
        String tipoNormalizado = tipoEmergencia.trim().toLowerCase();

        System.out.println();
        System.out.println("=== INVENTARIO DEL VEHÍCULO ===");
        System.out.println("Gravedad detectada: " + gravedad);

        System.out.println("Recursos base disponibles:");
        System.out.println("- Botiquín");
        System.out.println("- Manta térmica");

        if (tipoNormalizado.equals("parada cardiaca") || tipoNormalizado.equals("parada cardíaca")) {
            System.out.println("- Desfibrilador: DESBLOQUEADO");
        } else {
            System.out.println("- Desfibrilador: disponible solo para emergencia cardiaca");
        }

        if (gravedad.equalsIgnoreCase("grave")) {
            System.out.println("- Señalización de emergencia recomendada");
            System.out.println("- Prioridad máxima de asistencia");
        }

        System.out.println("===============================");
        System.out.println();
    }
}