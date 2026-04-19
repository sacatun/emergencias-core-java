package com.emergencias.modelo;

import java.util.List;

public class ResultadoEmergencia {
    private EventoEmergencia evento;
    private String resumenAlerta;
    private String protocolo;
    private String inventario;
    private List<CentroSalud> centrosSalud;
    private String textoCentros;

    public ResultadoEmergencia(EventoEmergencia evento,
                               String resumenAlerta,
                               String protocolo,
                               String inventario,
                               List<CentroSalud> centrosSalud,
                               String textoCentros) {
        this.evento = evento;
        this.resumenAlerta = resumenAlerta;
        this.protocolo = protocolo;
        this.inventario = inventario;
        this.centrosSalud = centrosSalud;
        this.textoCentros = textoCentros;
    }

    public EventoEmergencia getEvento() {
        return evento;
    }

    public String getResumenAlerta() {
        return resumenAlerta;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public String getInventario() {
        return inventario;
    }

    public List<CentroSalud> getCentrosSalud() {
        return centrosSalud;
    }

    public String getTextoCentros() {
        return textoCentros;
    }
}