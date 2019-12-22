package com.example.tucshop.Modelo;

public class Productos {

    private String imagenProducto;
    private String nombreProducto;
    private String montoProducto;
    private String gpu;

    public Productos() {
    }

    public Productos(String imagenProducto, String nombreProducto, String montoProducto, String gpu) {
        this.imagenProducto = imagenProducto;
        this.nombreProducto = nombreProducto;
        this.montoProducto = montoProducto;
        this.gpu = gpu;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
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

    public String getGpu(){
        return gpu;
    }

    public void setGpu(String gpu){this.gpu = gpu;}
}
