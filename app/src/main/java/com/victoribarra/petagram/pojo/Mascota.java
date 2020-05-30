package com.victoribarra.petagram.pojo;

public class Mascota {
    private int id;
    private String nombre;
    private int likes;
    private int foto;
    private boolean liked ;

    public Mascota(String nombre,int foto,int likes){
        this.nombre = nombre;
        this.likes  = likes;
        this.foto   = foto;
        this.liked  = false;



    }

    public Mascota() {

    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
