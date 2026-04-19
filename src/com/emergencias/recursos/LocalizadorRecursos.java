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

    public List<CentroSalud> obtenerCentrosPorMunicipio(String municipioBuscado) {
        List<CentroSalud> encontrados = buscarPorMunicipio(municipioBuscado);
        int limite = Math.min(encontrados.size(), 3);
        return new ArrayList<>(encontrados.subList(0, limite));
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

    public String formatearCentros(List<CentroSalud> centros, String municipioBuscado) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== RECURSOS SANITARIOS CERCANOS ===\n");

        if (centros == null || centros.isEmpty()) {
            resultado.append("No se han encontrado centros de salud para el municipio: ")
                    .append(municipioBuscado);
        } else {
            resultado.append("Mostrando hasta ")
                    .append(centros.size())
                    .append(" centros encontrados en ")
                    .append(municipioBuscado)
                    .append(":\n\n");

            for (int i = 0; i < centros.size(); i++) {
                CentroSalud centro = centros.get(i);
                resultado.append(i + 1).append(". ").append(valorSeguro(centro.getNombre())).append("\n");
                resultado.append("   Dirección: ").append(valorSeguro(centro.getDireccion())).append("\n");
                resultado.append("   Municipio: ").append(valorSeguro(centro.getMunicipio())).append("\n");
                resultado.append("   Teléfono: ").append(valorSeguro(centro.getTelefono())).append("\n\n");
            }
        }

        resultado.append("====================================");
        return resultado.toString();
    }

    private String valorSeguro(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return "No disponible";
        }
        return valor;
    }
}