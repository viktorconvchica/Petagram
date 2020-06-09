package com.victoribarra.petagram.pojo;

public class Mascota {
    private String id;
    private String username;
    private int likes;
    private String urlfoto;
    private String caption;


    public Mascota(String username,String urlfoto,int likes){
        this.username = username;
        this.likes  = likes;
        this.urlfoto   = urlfoto;

    }

    public Mascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
