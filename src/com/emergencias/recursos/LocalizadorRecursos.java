package com.emergencias.recursos;

import com.emergencias.datos.CargadorCentrosSalud;
import com.emergencias.modelo.CentroSalud;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class LocalizadorRecursos {

    private final ArrayList<CentroSalud> centros;

    public LocalizadorRecursos() {
        this.centros = CargadorCentrosSalud.cargar();
    }

    public void mostrarCentrosPorMunicipio(String municipioBuscado) {
        System.out.println();
        System.out.println("=== RECURSOS SANITARIOS CERCANOS ===");

        List<CentroSalud> encontrados = buscarPorMunicipio(municipioBuscado);

        if (encontrados.isEmpty()) {
            System.out.println("No se han encontrado centros de salud para el municipio: " + municipioBuscado);
        } else {
            int limite = Math.min(encontrados.size(), 3);
            System.out.println("Mostrando hasta " + limite + " centros encontrados en " + municipioBuscado + ":");

            for (int i = 0; i < limite; i++) {
                CentroSalud centro = encontrados.get(i);
                System.out.println((i + 1) + ". " + centro.getNombre());
                System.out.println("   Dirección: " + valorSeguro(centro.getDireccion()));
                System.out.println("   Municipio: " + valorSeguro(centro.getMunicipio()));
                System.out.println("   Teléfono: " + valorSeguro(centro.getTelefono()));
                System.out.println();
            }
        }

        System.out.println("====================================");
        System.out.println();
    }

    private List<CentroSalud> buscarPorMunicipio(String municipioBuscado) {
        List<CentroSalud> resultado = new ArrayList<>();
        String municipioNormalizado = normalizar(municipioBuscado);

        for (CentroSalud centro : centros) {
            String municipioCentro = normalizar(centro.getMunicipio());
            if (!municipioCentro.isEmpty() && municipioCentro.contains(municipioNormalizado)) {
                resultado.add(centro);
            }
        }

        return resultado;
    }

    private String normalizar(String texto) {
        if (texto == null) {
            return "";
        }

        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return sinTildes.trim().toLowerCase();
    }

    private String valorSeguro(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return "No disponible";
        }
        return valor;
    }
}