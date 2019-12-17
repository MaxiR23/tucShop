package com.example.tucshop.Modelo;

public class Domicilio {

    private String provincia;
    private String Localidad;
    private int CódigoPostal;

    public Domicilio() {
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public int getCódigoPostal() {
        return CódigoPostal;
    }

    public void setCódigoPostal(int códigoPostal) {
        CódigoPostal = códigoPostal;
    }
}
