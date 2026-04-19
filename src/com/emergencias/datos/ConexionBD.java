package com.emergencias.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL_DEFECTO = "jdbc:mysql://localhost:3306/emergencias";
    private static final String USUARIO_DEFECTO = "root";
    private static final String PASSWORD_DEFECTO = "";

    public Connection abrir() throws SQLException {
        String url = obtenerVariableEntorno("EMERGENCIAS_DB_URL", URL_DEFECTO);
        String usuario = obtenerVariableEntorno("EMERGENCIAS_DB_USER", USUARIO_DEFECTO);
        String password = obtenerVariableEntorno("EMERGENCIAS_DB_PASSWORD", PASSWORD_DEFECTO);

        return DriverManager.getConnection(url, usuario, password);
    }

    private String obtenerVariableEntorno(String nombre, String valorDefecto) {
        String valor = System.getenv(nombre);
        if (valor == null || valor.trim().isEmpty()) {
            return valorDefecto;
        }
        return valor;
    }
}
