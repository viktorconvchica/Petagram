package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class favoritos extends AppCompatActivity {

    private RecyclerView recyclerfavoritos;
    private ArrayList<Mascota> favoritos;
    private ConstructorMascotas constructorMascotas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        inicializartoolbar();
        recyclerfavoritos = findViewById(R.id.rvfavoritos);
        generarLayoutManager();
        inicializarfavoritos();
        inicializarAdaptador();
    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Favoritos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void inicializarfavoritos (){
        favoritos= new ArrayList<Mascota>();

        constructorMascotas= new ConstructorMascotas(getBaseContext());
        favoritos= constructorMascotas.obtenerFavoritos();

    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(favoritos,this);
        recyclerfavoritos.setAdapter(adaptador);

    }

    public void generarLayoutManager (){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerfavoritos.setLayoutManager(llm);
    }
}
