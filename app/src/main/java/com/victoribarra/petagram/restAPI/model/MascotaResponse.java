package com.victoribarra.petagram.restAPI.model;

import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
