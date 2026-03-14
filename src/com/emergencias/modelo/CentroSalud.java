package com.emergencias.modelo;

import com.google.gson.annotations.SerializedName;

public class CentroSalud {

    @SerializedName("Código")
    private String codigo;

    @SerializedName("Nombre")
    private String nombre;

    @SerializedName("Dirección")
    private String direccion;

    @SerializedName("C.P.")
    private String codigoPostal;

    @SerializedName("Municipio")
    private String municipio;

    @SerializedName("Pedanía")
    private String pedania;

    @SerializedName("Teléfono")
    private String telefono;

    @SerializedName("Fax")
    private String fax;

    @SerializedName("Email")
    private String email;

    @SerializedName("URL Real")
    private String urlReal;

    @SerializedName("URL Corta")
    private String urlCorta;

    @SerializedName("Latitud")
    private String latitud;

    @SerializedName("Longitud")
    private String longitud;

    @SerializedName("Foto 1")
    private String foto1;

    @SerializedName("Foto 2")
    private String foto2;

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getMunicipio() { return municipio; }
    public String getPedania() { return pedania; }
    public String getTelefono() { return telefono; }
    public String getFax() { return fax; }
    public String getEmail() { return email; }
    public String getUrlReal() { return urlReal; }
    public String getUrlCorta() { return urlCorta; }
    public String getLatitud() { return latitud; }
    public String getLongitud() { return longitud; }
    public String getFoto1() { return foto1; }
    public String getFoto2() { return foto2; }

    @Override
    public String toString() {
        return nombre + " (" + municipio + ") - " + direccion;
    }
}
