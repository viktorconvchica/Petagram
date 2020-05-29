package com.victoribarra.petagram.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victoribarra.petagram.R;
import com.victoribarra.petagram.adapter.PerfilAdaptador;
import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil_fragment extends Fragment {
    private RecyclerView rvperfil;
    private ArrayList<Mascota> mascotas;

    public Perfil_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_perfil, container, false);
        // Inflate the layout for this fragment
        rvperfil = v.findViewById(R.id.rvperfil);
        rvperfil.setLayoutManager(new GridLayoutManager(getActivity(),3));

        inicializarlistamascotas();
        inicializarAdaptador();
        return v;
    }


    public void inicializarlistamascotas (){
        mascotas= new ArrayList<Mascota>();

        mascotas.add(new Mascota("perro1",R.drawable.perro1,10));
        mascotas.add(new Mascota("perro2",R.drawable.perro2,10));
        mascotas.add(new Mascota("perro3",R.drawable.perro3,10));
        mascotas.add(new Mascota("perro4",R.drawable.perro4,10));
        mascotas.add(new Mascota("perro5",R.drawable.perro5,10));
        mascotas.add(new Mascota("perro6",R.drawable.perro6,10));
        mascotas.add(new Mascota("perro7",R.drawable.perro7,10));
        mascotas.add(new Mascota("perro1",R.drawable.perro1,10));
        mascotas.add(new Mascota("perro2",R.drawable.perro2,10));
        mascotas.add(new Mascota("perro3",R.drawable.perro3,10));
        mascotas.add(new Mascota("perro4",R.drawable.perro4,10));
        mascotas.add(new Mascota("perro5",R.drawable.perro5,10));
        mascotas.add(new Mascota("perro6",R.drawable.perro6,10));
        mascotas.add(new Mascota("perro7",R.drawable.perro7,10));
    }

    public void inicializarAdaptador(){
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas);
        rvperfil.setAdapter(adaptador);

    }
}
