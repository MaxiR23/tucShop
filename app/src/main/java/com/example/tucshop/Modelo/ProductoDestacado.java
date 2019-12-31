package com.example.tucshop.Modelo;

public class ProductoDestacado {

    private String imagenProducto;
    private String nombreProducto;
    private String montoProducto;
    private String altura;
    private String ancho;
    private String bateria;
    private String camaraFrontal;
    private String camaraPrincipal;
    private String cantidadParlantes;
    private String capacidadBateria;
    private String gpu;
    private String hdmi;
    private String linea;
    private String marca;
    private String marcaProcesador;
    private String memoriaRam;
    private String memoriaInterna;
    private String modeloProcesador;
    private String nucleos;
    private String peso;
    private String red;
    private String resolucion;
    private String so;
    private String tamañoPantalla;
    private String tipoPantalla;
    private String usb;
    private String versionSO;
    private String wifi;

    public ProductoDestacado() {
    }

    public ProductoDestacado(String imagenProducto, String nombreProducto, String montoProducto, String altura, String ancho, String bateria,
                     String camaraFrontal, String camaraPrincipal, String cantidadParlantes, String capacidadBateria, String gpu, String hdmi,
                     String linea, String marca, String marcaProcesador, String memoriaRam, String memoriaInterna, String modeloProcesador, String nucleos, String peso,
                     String red, String resolucion, String so, String tamañoPantalla, String tipoPantalla, String usb, String versionSO, String wifi)
    {
        this.imagenProducto = imagenProducto;
        this.nombreProducto = nombreProducto;
        this.montoProducto = montoProducto;
        this.altura = altura;
        this.ancho = ancho;
        this.bateria = bateria;
        this.camaraFrontal = camaraFrontal;
        this.camaraPrincipal = camaraPrincipal;
        this.cantidadParlantes = cantidadParlantes;
        this.capacidadBateria = capacidadBateria;
        this.gpu = gpu;
        this.hdmi = hdmi;
        this.linea = linea;
        this.marca = marca;
        this.marcaProcesador = marcaProcesador;
        this.memoriaRam = memoriaRam;
        this.memoriaInterna = memoriaInterna;
        this.modeloProcesador = modeloProcesador;
        this.nucleos = nucleos;
        this.peso = peso;
        this.red = red;
        this.resolucion = resolucion;
        this.so = so;
        this.tamañoPantalla = tamañoPantalla;
        this.tipoPantalla = tipoPantalla;
        this.usb = usb;
        this.versionSO = versionSO;
        this.wifi = wifi;
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

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getCamaraFrontal() {
        return camaraFrontal;
    }

    public void setCamaraFrontal(String camaraFrontal) {
        this.camaraFrontal = camaraFrontal;
    }

    public String getCamaraPrincipal() {
        return camaraPrincipal;
    }

    public void setCamaraPrincipal(String camaraPrincipal) {
        this.camaraPrincipal = camaraPrincipal;
    }

    public String getCantidadParlantes() {
        return cantidadParlantes;
    }

    public void setCantidadParlantes(String cantidadParlantes) {
        this.cantidadParlantes = cantidadParlantes;
    }

    public String getCapacidadBateria() {
        return capacidadBateria;
    }

    public void setCapacidadBateria(String capacidadBateria) {
        this.capacidadBateria = capacidadBateria;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getHdmi() {
        return hdmi;
    }

    public void setHdmi(String hdmi) {
        this.hdmi = hdmi;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarcaProcesador(){
        return marcaProcesador;
    }

    public void setMarcaProcesador(String marcaProcesador){
        this.marcaProcesador = marcaProcesador;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public String getMemoriaInterna() {
        return memoriaInterna;
    }

    public void setMemoriaInterna(String memoriaInterna) {
        this.memoriaInterna = memoriaInterna;
    }

    public String getModeloProcesador() {
        return modeloProcesador;
    }

    public void setModeloProcesador(String modeloProcesador) {
        this.modeloProcesador = modeloProcesador;
    }

    public String getNucleos() {
        return nucleos;
    }

    public void setNucleos(String nucleos) {
        this.nucleos = nucleos;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getTamañoPantalla() {
        return tamañoPantalla;
    }

    public void setTamañoPantalla(String tamañoPantalla) {
        this.tamañoPantalla = tamañoPantalla;
    }

    public String getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(String tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }

    public String getVersionSO() {
        return versionSO;
    }

    public void setVersionSO(String versionSO) {
        this.versionSO = versionSO;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

}
