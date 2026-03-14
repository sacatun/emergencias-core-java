package com.emergencias.datos;

import com.emergencias.modelo.CentroSalud;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CargadorCentrosSalud {

    private static final String RUTA_ARCHIVO = "src/com/emergencias/resources/centros_salud_murcia.json";

    public static ArrayList<CentroSalud> cargar() {
        try (Reader reader = new InputStreamReader(
                new FileInputStream(RUTA_ARCHIVO),
                StandardCharsets.UTF_8
        )) {
            Type tipoLista = new TypeToken<List<CentroSalud>>() {}.getType();
            List<CentroSalud> lista = new Gson().fromJson(reader, tipoLista);
            return new ArrayList<>(lista);

        } catch (Exception e) {
            throw new RuntimeException("Error cargando centros de salud desde JSON", e);
        }

    }

    private CargadorCentrosSalud() {
        // no instanciable
    }
}
