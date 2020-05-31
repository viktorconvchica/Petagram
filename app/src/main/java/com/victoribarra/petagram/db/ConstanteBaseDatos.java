package com.victoribarra.petagram.db;

public  final class ConstanteBaseDatos {

    public static final  String DATABASE_NAME = "mascotas";
    public static final  int DATABASE_VERSION = 1;

    public static final String  TABLE_MASCOTAS = "mascota";
    public static final String  TABLE_MASCOTAS_ID = "id";
    public static final String  TABLE_MASCOTAS_NOMBRE = "nombre";
    public static final String  TABLE_MASCOTAS_FOTO = "foto";

    public static final String TABLE_LIKES = "likes";
    public static final String TABLE_LIKES_ID = "id";
    public static final String TABLE_LIKES_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKES_TOTAL = "total";

    public static final  String TABLE_FAVORITOS = "favoritos";
    public static final  String TABLE_FAVORITOS_ID = "id_favoritos";
    public static final  String TABLE_FAVORITOS_MASCOTA_NOMBRE = "mascota_nombre";
    public static final  String TABLE_FAVORITOS_MASCOTA_FOTO = "mascota_foto";
    public static final  String TABLE_FAVORITOS_MASCOTA_LIKES = "mascota_likes";
}
