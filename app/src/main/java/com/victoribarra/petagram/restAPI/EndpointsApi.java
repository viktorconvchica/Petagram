package com.victoribarra.petagram.restAPI;

import com.victoribarra.petagram.restAPI.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_INFORMATION)
    Call<MascotaResponse> ObtenerMedia ();

    @GET()
    Call<MascotaResponse> ObtenerPer(@Url String url);


}
