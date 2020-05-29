package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class favoritos extends AppCompatActivity {

    private RecyclerView recyclerfavoritos;
    private ArrayList<Mascota> favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        inicializartoolbar();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerfavoritos = findViewById(R.id.rvfavoritos);
        recyclerfavoritos.setLayoutManager(llm);
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

        favoritos.add(new Mascota("perro1",R.drawable.perro1,10));
        favoritos.add(new Mascota("perro2",R.drawable.perro2,10));
        favoritos.add(new Mascota("perro3",R.drawable.perro3,10));
        favoritos.add(new Mascota("perro4",R.drawable.perro4,10));
        favoritos.add(new Mascota("perro5",R.drawable.perro5,10));

    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(favoritos);
        recyclerfavoritos.setAdapter(adaptador);

    }
}
