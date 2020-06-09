package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cuenta extends AppCompatActivity {

    public EditText etCuenta;
    public Button btnCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        inicializartoolbar();
        etCuenta = findViewById(R.id.etCuenta);
        btnCuenta = findViewById(R.id.btnCuenta);

        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}