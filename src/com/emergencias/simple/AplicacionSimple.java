package com.emergencias.simple;


public class AplicacionSimple {
    public static void main(String[] args) {
        DetectorSimple detector = new DetectorSimple();
        GestorSimple gestor = new GestorSimple();
        EnviadorSimple enviador = new EnviadorSimple();

        int nivel = detector.detectarNivel();
        String mensaje = gestor.evaluarNivel(nivel);
        enviador.enviar(mensaje);
    }
}
