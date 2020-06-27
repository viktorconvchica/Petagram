package com.victoribarra.petagram.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.R;
import com.victoribarra.petagram.restAPI.EndpointsApi;
import com.victoribarra.petagram.restAPI.adapter.RestAPIAdapter;
import com.victoribarra.petagram.restAPI.model.LikeResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    private  ArrayList<Mascota> mascotas;
    Activity activity;
    Context context = activity;
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
              String idDispositivo = preferences.getString("idDispositivo",null);
              if (idDispositivo !=null){
                  Toast.makeText(activity,"si paso el dispositivo",Toast.LENGTH_SHORT).show();
                  registrarLikeFirebase(datos.getId(),idDispositivo,datos.getUsername());
              }
              else{
                  Toast.makeText(activity,"habilita tus notificaciones",Toast.LENGTH_SHORT).show();
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

    public void registrarLikeFirebase (String idFoto,String idUsuario,String idDispositivo){
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionHeroku();
        Call<LikeResponse> likeResponseCall = endpointsApi.registrarLike(idFoto,idUsuario,idDispositivo);

        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });

    }

}
