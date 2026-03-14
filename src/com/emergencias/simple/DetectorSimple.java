package com.emergencias.simple;

import java.util.Scanner;

public class DetectorSimple {
    Scanner sc = new Scanner(System.in);

    public int detectarNivel(){
        System.out.println("Ingrese el nivel de la amenaza:");
        int nivel = sc.nextInt();
        return nivel;
    }
}
