package com.victoribarra.petagram.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    private  ArrayList<Mascota> mascotas;
    Activity activity;
    private int favoritossize ;
    private int updateindex ;

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
      mascotaViewHolder.tvCaption.setText(datos.getCaption());
      mascotaViewHolder.tvLikesCV.setText(String.valueOf(datos.getLikes()));
      Picasso.get().load(datos.getUrlfoto()).into(mascotaViewHolder.imgFotoCV);
      //mascotaViewHolder.imgFotoCV.setImageResource(datos.getUrlfoto());



      mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
              constructorMascotas.darLikeMascota(datos);
              mascotaViewHolder.tvLikesCV.setText(String.valueOf( constructorMascotas.obtenerLikeMascota(datos)));
              SharedPreferences preferences;
              preferences = activity.getSharedPreferences("Share", Context.MODE_PRIVATE);
              favoritossize = preferences.getInt("size",1);
              updateindex = preferences.getInt("index",1);
              constructorMascotas.insertar1favorito(datos,favoritossize,updateindex);
              favoritossize++;
              SharedPreferences.Editor editor = preferences.edit();
              editor.putInt("size",favoritossize);
              editor.commit();
              if (favoritossize>=4){
                  updateindex= preferences.getInt("index",0);
                  updateindex++;
                  editor.putInt("index",updateindex);
                  editor.commit();
              }
              if (updateindex>5){
                  updateindex=1;
                  editor.putInt("index",updateindex);
                  editor.commit();
              }





          }
      });

    }

    @Override
    public int getItemCount() {

        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

         private TextView tvCaption;
         private TextView tvLikesCV;
         private ImageView imgFotoCV;
         private ImageButton btnLike;


        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption   = itemView.findViewById(R.id.tvCaption);
            imgFotoCV   = itemView.findViewById(R.id.imgFoto);
            btnLike     = itemView.findViewById(R.id.btnLike);
            tvLikesCV   = itemView.findViewById(R.id.tvLikes);

        }
    }
}
