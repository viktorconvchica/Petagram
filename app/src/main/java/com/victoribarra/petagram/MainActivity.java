package com.victoribarra.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList <Mascota> mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializartoolbar();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.rvMascota);
        recyclerView.setLayoutManager(llm);
        inicializarlistamascotas();
        inicializarAdaptador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favoritos:
                Intent favoritos = new Intent(this, favoritos.class );
                startActivity(favoritos);
                return true;
            case R.id.mContacto:
                Intent contactos = new Intent(this,Contacto.class);
                startActivity(contactos);
                return true;
            case R.id.mAbout:
                Intent about = new Intent(this,acerca.class);
                startActivity(about);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }

    public void inicializarlistamascotas (){
        mascotas= new ArrayList<Mascota>();

        mascotas.add(new Mascota("perro1",R.drawable.perro1,10));
        mascotas.add(new Mascota("perro2",R.drawable.perro2,10));
        mascotas.add(new Mascota("perro3",R.drawable.perro3,10));
        mascotas.add(new Mascota("perro4",R.drawable.perro4,10));
        mascotas.add(new Mascota("perro5",R.drawable.perro5,10));
        mascotas.add(new Mascota("perro6",R.drawable.perro6,10));
        mascotas.add(new Mascota("perro7",R.drawable.perro7,10));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        recyclerView.setAdapter(adaptador);


    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Petagram");
    }
}
