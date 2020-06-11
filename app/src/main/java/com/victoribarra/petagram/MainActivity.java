package com.victoribarra.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.victoribarra.petagram.adapter.Page_adapter;
import com.victoribarra.petagram.fragment.Perfil_fragment;
import com.victoribarra.petagram.fragment.Recyclerview_fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    public String token ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializartoolbar();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            token =extras.getString("token");


        }
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();



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
}
