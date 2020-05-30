package com.victoribarra.petagram.presentador;

import android.content.Context;

import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.fragment.IReciclerViewFragmentView;
import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class ReciclerViewFragmentPresenter implements IReciclerviewFragmentPresenter {

    private IReciclerViewFragmentView iReciclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public ReciclerViewFragmentPresenter(IReciclerViewFragmentView iReciclerViewFragmentView, Context context){
        this.iReciclerViewFragmentView = iReciclerViewFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }
    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas= new ConstructorMascotas(context);
        mascotas= constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void mostrarMascotasRV() {

        iReciclerViewFragmentView.inicializarAdaptadorRV(iReciclerViewFragmentView.crearAdaptador(mascotas));
        iReciclerViewFragmentView.generarLinearLayoutVertical();

    }
}
