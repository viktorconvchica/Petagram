package com.victoribarra.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.R;

import java.util.ArrayList;

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {
    private  ArrayList<Mascota> mascotas;

    public PerfilAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas =mascotas;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);

        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PerfilViewHolder perfilViewHolder, int position) {
        final Mascota datos = mascotas.get(position);
        perfilViewHolder.tvCaptionPerfil.setText(String.valueOf(datos.getLikes()));
        //perfilViewHolder.imgFotoCV.setImageResource(datos.get());

    }

    @Override
    public int getItemCount() {

        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {


        private TextView tvCaptionPerfil;
        private ImageView imgFotoCV;


        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCaptionPerfil   = itemView.findViewById(R.id.tvcaptionperfil);
            imgFotoCV         = itemView.findViewById(R.id.imgPerfil);

        }
    }
}
