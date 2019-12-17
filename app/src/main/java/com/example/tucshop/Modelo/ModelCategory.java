package com.example.tucshop.Modelo;

public class ModelCategory {

    private int imagenProducto;
    private String titulo;
    private String descripción;

    public ModelCategory() {
    }

    public ModelCategory(int imagenProducto, String titulo, String descripción) {
        this.imagenProducto = imagenProducto;
        this.titulo = titulo;
        this.descripción = descripción;
    }

    public int getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(int imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
}
