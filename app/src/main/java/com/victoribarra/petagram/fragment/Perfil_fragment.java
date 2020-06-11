package com.victoribarra.petagram.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.victoribarra.petagram.Cuenta;
import com.victoribarra.petagram.MainActivity;
import com.victoribarra.petagram.R;
import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.adapter.PerfilAdaptador;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.presentador.IReciclerviewFragmentPresenter;
import com.victoribarra.petagram.presentador.ReciclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil_fragment extends Fragment implements IReciclerViewFragmentView {
    private RecyclerView rvperfil;
    private ArrayList<Mascota> mascotas;
    private IReciclerviewFragmentPresenter presenter;
    private String token;
    private TextView tvperfil;
    private CircularImageView fotoperfil;

    public Perfil_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_perfil, container, false);
        // Inflate the layout for this fragment
        rvperfil = v.findViewById(R.id.rvperfil);
        tvperfil = v.findViewById(R.id.tvusername);
        fotoperfil = v.findViewById(R.id.fotoperfil);


        MainActivity activity = (MainActivity) getActivity();
        token = activity.getToken();
        if (token != null){
            presenter = new ReciclerViewFragmentPresenter(this, getContext(), token);

        }
        return v;
    }


    public void inicializarlistamascotas (){
        mascotas= new ArrayList<Mascota>();
/*
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
        mascotas.add(new Mascota("perro7",R.drawable.perro7,10));*/
    }

    public void setToken (String token){
        this.token = token;

    }



    @Override
    public void generarLinearLayoutVertical() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        rvperfil.setLayoutManager(gridLayoutManager);
      /*  LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvperfil.setLayoutManager(llm); */

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvperfil.setAdapter(adaptador);

    }

    @Override
    public PerfilAdaptador crearperfiladaptador(ArrayList<Mascota> mascotas) {
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas,getActivity());
        Mascota perfil = mascotas.get(0);
        tvperfil.setText(perfil.getUsername());
        Picasso.get().load(perfil.getUrlfoto()).into(fotoperfil);
        return adaptador;
    }

    @Override
    public void inicializaradaptadorper(PerfilAdaptador adaptador) {
        rvperfil.setAdapter(adaptador);
    }


}
