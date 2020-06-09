package com.victoribarra.petagram.restAPI;

import com.victoribarra.petagram.restAPI.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_INFORMATION)
    Call<MascotaResponse> ObtenerMedia ();
}
