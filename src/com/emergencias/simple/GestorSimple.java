package com.emergencias.simple;

public class GestorSimple {
    public String evaluarNivel(int nivel) {
        if (nivel >= 5) {
            return "ALERTA";
        } else {
            return "Nada detectado";
        }
    }
}
