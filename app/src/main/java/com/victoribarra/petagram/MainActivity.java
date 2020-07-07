package com.victoribarra.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.victoribarra.petagram.adapter.MascotaAdaptador;
import com.victoribarra.petagram.adapter.Page_adapter;
import com.victoribarra.petagram.fragment.Perfil_fragment;
import com.victoribarra.petagram.fragment.Recyclerview_fragment;
import com.victoribarra.petagram.restAPI.EndpointsApi;
import com.victoribarra.petagram.restAPI.adapter.RestAPIAdapter;
import com.victoribarra.petagram.restAPI.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public String token ;
    public String idDispositivo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializartoolbar();
        SharedPreferences preferences;
        preferences = MainActivity.this.getSharedPreferences("Share", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            editor.putString("tokenperfil",extras.getString("token"));
            editor.commit();
        }
        token= preferences.getString("tokenperfil",null);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();

        viewPager.setCurrentItem(1);

    }

    public String getToken(){
        return token;
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

            case R.id.mCuenta:
                Intent Cuenta = new Intent(this, Cuenta.class);
                startActivity(Cuenta);
                return true;

            case R.id.mNotificaciones:
                recibirNotificaciones();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
    private ArrayList<Fragment> agregarfragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new Recyclerview_fragment());
        fragments.add(new Perfil_fragment());
        return fragments;

    }
    private void setUpViewPager(){
        viewPager.setAdapter(new Page_adapter(getSupportFragmentManager(), agregarfragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_cucha_64);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_perro_64);

    }



    public void inicializartoolbar(){
        Toolbar miActionBar = findViewById(R.id.toolbar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setTitle("     Petagram");
    }

    public void recibirNotificaciones(){
        if (idDispositivo != null){
            if (token != null){
                RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
                EndpointsApi endpointsApi = restAPIAdapter.establecerConexionHeroku();
                Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarUsuario(idDispositivo,token);

                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse = response.body();
                        Log.d("idAutogenerado",usuarioResponse.getIdAutogenerado());
                        Log.d("idDispositivo",usuarioResponse.getDispositivo());
                        Log.d("idInsta",usuarioResponse.getInstagram());
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });
            }
            else{
                Toast.makeText(MainActivity.this, "configura tu cuenta de instagram", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            obtenerIdDispositivo();
            if (idDispositivo != null) {
                recibirNotificaciones();
            }
        }
    }

    public void obtenerIdDispositivo(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);

                       // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        //guardar token de dispositivo en shared preferences
                        SharedPreferences preferences;
                        preferences = MainActivity.this.getSharedPreferences("Share", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("idDispositivo",token);
                        editor.commit();
                        idDispositivo= preferences.getString("idDispositivo",null);


                    }
                });

    }
}
