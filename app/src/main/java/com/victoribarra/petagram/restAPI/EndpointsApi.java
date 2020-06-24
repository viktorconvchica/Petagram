package com.victoribarra.petagram.restAPI;

import com.victoribarra.petagram.restAPI.model.MascotaResponse;
import com.victoribarra.petagram.restAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_INFORMATION)
    Call<MascotaResponse> ObtenerMedia ();

    @GET()
    Call<MascotaResponse> ObtenerPer(@Url String url);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_REGISTRO)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo,@Field("id_usuario_instagram")String id_instagram);



}
