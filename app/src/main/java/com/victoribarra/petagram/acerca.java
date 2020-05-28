package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        inicializartoolbar();
    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Acerca de");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
