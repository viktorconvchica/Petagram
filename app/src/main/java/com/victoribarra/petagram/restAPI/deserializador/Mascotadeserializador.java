package com.victoribarra.petagram.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.victoribarra.petagram.pojo.Mascota;
import com.victoribarra.petagram.restAPI.JsonKeys;
import com.victoribarra.petagram.restAPI.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Mascotadeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse= gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData  = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarMascotaJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotaJson (JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            String username = mascotaResponseDataObject.get(JsonKeys.USERNAME).getAsString();
            String caption  = mascotaResponseDataObject.get(JsonKeys.CAPTION).getAsString();
            String imagen   = mascotaResponseDataObject.get(JsonKeys.IMAGEN).getAsString();
            String id       = mascotaResponseDataObject.get("id").getAsString();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setUsername(username);
            mascotaActual.setCaption(caption);
            mascotaActual.setUrlfoto(imagen);
            mascotaActual.setId(id);

            mascotas.add(mascotaActual);


        }
        return mascotas;
    }
}
