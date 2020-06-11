package com.victoribarra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.victoribarra.petagram.fragment.Perfil_fragment;
import com.victoribarra.petagram.presentador.IReciclerviewFragmentPresenter;
import com.victoribarra.petagram.presentador.ReciclerViewFragmentPresenter;
import com.victoribarra.petagram.restAPI.ConstantesRestApi;

public class Cuenta extends AppCompatActivity {

    public EditText etCuenta;
    public Button btnCuenta;
    public String usuario;
    public String token;
    private IReciclerviewFragmentPresenter presenter;



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
                usuario= etCuenta.getText().toString();
                switch (usuario){
                    case ConstantesRestApi.USUARIO1:
                        token=ConstantesRestApi.ACCESS_TOKEN;
                        break;
                    case ConstantesRestApi.USUARIO2:
                        token=ConstantesRestApi.ACCESS_TOKEN2;
                        break;
                    default:
                        Toast.makeText(Cuenta.this,"no valido",Toast.LENGTH_SHORT).show();
                        break;

                }

                Intent Cuenta = new Intent(Cuenta.this, MainActivity.class);
                Cuenta.putExtra("token",token);
                startActivity(Cuenta);
            }
        });
    }

    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.appbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Configurar cuenta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}