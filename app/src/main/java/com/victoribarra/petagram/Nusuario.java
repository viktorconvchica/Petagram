package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.restAPI.EndpointsApi;
import com.victoribarra.petagram.restAPI.adapter.RestAPIAdapter;
import com.victoribarra.petagram.restAPI.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.victoribarra.petagram.restAPI.ConstantesRestApi.ACCESS_TOKEN2;

public class Nusuario extends AppCompatActivity {
    private RecyclerView recyclerusuario;
    private ArrayList<Mascota> usuarios;
    private ConstructorMascotas constructorMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nusuario);

        inicializartoolbar();
        recyclerusuario = findViewById(R.id.rvusuarios);
        generarLayoutManager();
        obtenerMedios();

    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("Usuario que te dio like");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(usuarios,this);
        recyclerusuario.setAdapter(adaptador);

    }

    public void generarLayoutManager (){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerusuario.setLayoutManager(llm);
    }

    public void obtenerMedios() {
        RestAPIAdapter restAPIAdapter =new RestAPIAdapter();
        Gson gsonmediarecent = restAPIAdapter.construyeGsonDeserializador();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestAPIInstagram(gsonmediarecent);
        String url = " https://graph.instagram.com/me/media?fields=media_url,caption,username&access_token="+ACCESS_TOKEN2 ;
        Call<MascotaResponse> mascotaResponseCall= endpointsApi.ObtenerPer(url);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                usuarios = mascotaResponse.getMascotas();
                inicializarAdaptador();


            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(Nusuario.this, "algo fallo",Toast.LENGTH_SHORT).show();
                Log.e("fallo la conexion", t.toString());

            }
        });

    }
}