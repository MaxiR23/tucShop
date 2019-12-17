package com.example.tucshop.Modelo;

public class Usuarios {

    private String id;
    private String nombre;
    private String apellido;
    private String imageURL;
    private String telefono;
    private String provincia;
    private String localidad;
    private String códigoPostal;

    public Usuarios() {
    }

    public Usuarios(String id, String nombre, String apellido, String imageURL, String telefono, String provincia, String localidad, String códigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.imageURL = imageURL;
        this.telefono = telefono;
        this.provincia = provincia;
        this.localidad = localidad;
        this.códigoPostal = códigoPostal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCódigoPostal() {
        return códigoPostal;
    }

    public void setCódigoPostal(String códigoPostal) {
        this.códigoPostal = códigoPostal;
    }
}
