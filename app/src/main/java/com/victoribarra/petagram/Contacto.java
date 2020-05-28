package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Contacto extends AppCompatActivity {
    public EditText etMail;
    public EditText etNombre;
    public EditText etMensaje;
    public Button   btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        inicializartoolbar();
        etMail = findViewById(R.id.etCorreo);
        etNombre = findViewById(R.id.etNombre);
        etMensaje= findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String correo = etMail.getText().toString().trim();
        String mensaje =etMensaje.getText().toString();
        String subject = etNombre.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this,correo,subject,mensaje);
        javaMailAPI.execute();
    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
