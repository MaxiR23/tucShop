package com.example.tucshop.Modelo;

public class Compras {

    private String nombreProducto;
    private String montoProducto;
    private String date;

    public Compras() {
    }

    public Compras(String nombreProducto, String montoProducto, String date) {
        this.nombreProducto = nombreProducto;
        this.montoProducto = montoProducto;
        this.date = date;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMontoProducto() {
        return montoProducto;
    }

    public void setMontoProducto(String montoProducto) {
        this.montoProducto = montoProducto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
