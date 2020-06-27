package com.victoribarra.petagram.restAPI.model;

public class LikeResponse {
    private String id;
    private String foto;
    private String usuario;
    private String dispositivo;

    public LikeResponse(String id, String foto, String usuario, String dispositivo) {
        this.id = id;
        this.foto = foto;
        this.usuario = usuario;
        this.dispositivo = dispositivo;
    }

    public LikeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
}
