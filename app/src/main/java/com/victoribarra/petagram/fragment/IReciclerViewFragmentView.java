package com.victoribarra.petagram.fragment;

import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public interface IReciclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador (ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
