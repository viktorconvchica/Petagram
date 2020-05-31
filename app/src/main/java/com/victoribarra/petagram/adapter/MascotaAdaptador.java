package com.victoribarra.petagram.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    private  ArrayList<Mascota> mascotas;
    Activity activity;


    public MascotaAdaptador(ArrayList<Mascota> mascotas,Activity activity){
        this.mascotas =mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
       return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
      final Mascota datos = mascotas.get(position);
      mascotaViewHolder.tvNombreCV.setText(datos.getNombre());
      mascotaViewHolder.tvLikesCV.setText(String.valueOf(datos.getLikes()));
      mascotaViewHolder.imgFotoCV.setImageResource(datos.getFoto());


      mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
              constructorMascotas.darLikeMascota(datos);
              mascotaViewHolder.tvLikesCV.setText(String.valueOf( constructorMascotas.obtenerLikeMascota(datos)));

          }
      });

    }

    @Override
    public int getItemCount() {

        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

         private TextView tvNombreCV;
         private TextView tvLikesCV;
         private ImageView imgFotoCV;
         private ImageButton btnLike;


        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCV  = itemView.findViewById(R.id.tvNombre);
            tvLikesCV   = itemView.findViewById(R.id.tvLikes);
            imgFotoCV   = itemView.findViewById(R.id.imgFoto);
            btnLike     = itemView.findViewById(R.id.btnLike);

        }
    }
}
