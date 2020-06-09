package com.victoribarra.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.victoribarra.petagram.db.ConstructorMascotas;
import com.victoribarra.petagram.fragment.IReciclerViewFragmentView;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.restAPI.EndpointsApi;
import com.victoribarra.petagram.restAPI.adapter.RestAPIAdapter;
import com.victoribarra.petagram.restAPI.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciclerViewFragmentPresenter implements IReciclerviewFragmentPresenter {

    private IReciclerViewFragmentView iReciclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public ReciclerViewFragmentPresenter(IReciclerViewFragmentView iReciclerViewFragmentView, Context context){
        this.iReciclerViewFragmentView = iReciclerViewFragmentView;
        this.context = context;
       // obtenerMascotasBaseDatos();
        obtenerMedios();
    }
    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas= new ConstructorMascotas(context);
        mascotas= constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void obtenerMedios() {
        RestAPIAdapter restAPIAdapter =new RestAPIAdapter();
        Gson gsonmediarecent = restAPIAdapter.construyeGsonDeserializador();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestAPIInstagram(gsonmediarecent);
        Call<MascotaResponse> mascotaResponseCall= endpointsApi.ObtenerMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "algo fallo",Toast.LENGTH_SHORT).show();
                Log.e("fallo la conexion", t.toString());

            }
        });

    }

    @Override
    public void mostrarMascotasRV() {

        iReciclerViewFragmentView.inicializarAdaptadorRV(iReciclerViewFragmentView.crearAdaptador(mascotas));
        iReciclerViewFragmentView.generarLinearLayoutVertical();

    }
}
