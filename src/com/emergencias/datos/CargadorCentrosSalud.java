package com.emergencias.datos;

import com.emergencias.modelo.CentroSalud;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CargadorCentrosSalud {

    public static ArrayList<CentroSalud> cargar() {
        InputStream input = CargadorCentrosSalud.class
                .getClassLoader()
                .getResourceAsStream("com/emergencias/resources/centros_salud_murcia.json");

        if (input == null) {
            throw new RuntimeException("No se encontró el archivo centros_salud_murcia.json en resources.");
        }

        try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            Type tipoLista = new TypeToken<List<CentroSalud>>() {}.getType();
            List<CentroSalud> lista = new Gson().fromJson(reader, tipoLista);
            return new ArrayList<>(lista);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando centros de salud desde JSON.", e);
        }
    }

    private CargadorCentrosSalud() {
        // no instanciable
    }
}