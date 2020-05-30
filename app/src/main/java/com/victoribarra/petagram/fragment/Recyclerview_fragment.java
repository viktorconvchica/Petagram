package com.victoribarra.petagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victoribarra.petagram.R;
import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.presentador.IReciclerviewFragmentPresenter;
import com.victoribarra.petagram.presentador.ReciclerViewFragmentPresenter;

import java.util.ArrayList;



public class Recyclerview_fragment extends Fragment implements IReciclerViewFragmentView {
    private RecyclerView recyclerView;
    private ArrayList<Mascota> mascotas;
    private IReciclerviewFragmentPresenter presenter;

    public Recyclerview_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = v.findViewById(R.id.rvMascota);
        // Inflate the layout for this fragment
        presenter = new ReciclerViewFragmentPresenter(this,getContext());
        return v;

    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        recyclerView.setAdapter(adaptador);

    }
}
