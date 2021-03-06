package com.victoribarra.petagram;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    private  ArrayList<Mascota> mascotas;

    public MascotaAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas =mascotas;
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
            if (datos.isLiked()){
                datos.setLiked(false);
                datos.setLikes(datos.getLikes()-1);
                mascotaViewHolder.btnLike.setColorFilter(Color.BLACK);
                mascotaViewHolder.tvLikesCV.setText(String.valueOf(datos.getLikes()));

            }
            else {
                datos.setLiked(true);
                datos.setLikes(datos.getLikes()+1);
                mascotaViewHolder.btnLike.setColorFilter(Color.BLUE);
                mascotaViewHolder.tvLikesCV.setText(String.valueOf(datos.getLikes()));

            }
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
