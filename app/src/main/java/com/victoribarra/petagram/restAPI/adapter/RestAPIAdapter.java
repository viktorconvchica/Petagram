package com.victoribarra.petagram.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victoribarra.petagram.restAPI.ConstantesRestApi;
import com.victoribarra.petagram.restAPI.EndpointsApi;
import com.victoribarra.petagram.restAPI.deserializador.Mascotadeserializador;
import com.victoribarra.petagram.restAPI.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIAdapter {
    public EndpointsApi establecerConexionRestAPIInstagram(Gson gson){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializador (){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new Mascotadeserializador());
        return gsonBuilder.create();
    }
}
