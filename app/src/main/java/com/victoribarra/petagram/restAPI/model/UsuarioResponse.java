package com.victoribarra.petagram.restAPI.model;

public class UsuarioResponse {

    private String id;
    private String dispositivo;
    private  String instagram;

    public UsuarioResponse(String idAutogenerado, String idDispositivo, String instagramToken) {
        this.id = idAutogenerado;
        this.dispositivo = idDispositivo;
        instagram = instagramToken;
    }

    public UsuarioResponse(){

    }

    public String getIdAutogenerado() {
        return id;
    }

    public void setIdAutogenerado(String idAutogenerado) {
        this.id = idAutogenerado;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
