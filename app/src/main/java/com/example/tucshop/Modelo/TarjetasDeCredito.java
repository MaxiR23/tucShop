package com.example.tucshop.Modelo;

public class TarjetasDeCredito {

    private String númeroDeTarjeta;
    private String validez;
    private String nombreImpreso;
    private String Documento;

    public TarjetasDeCredito() {
    }

    public String getNúmeroDeTarjeta() {
        return númeroDeTarjeta;
    }

    public void setNúmeroDeTarjeta(String númeroDeTarjeta) {
        this.númeroDeTarjeta = númeroDeTarjeta;
    }

    public String getValidez() {
        return validez;
    }

    public void setValidez(String validez) {
        this.validez = validez;
    }

    public String getNombreImpreso() {
        return nombreImpreso;
    }

    public void setNombreImpreso(String nombreImpreso) {
        this.nombreImpreso = nombreImpreso;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }
}
