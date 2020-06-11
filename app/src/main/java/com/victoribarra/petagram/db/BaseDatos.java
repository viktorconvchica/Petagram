package com.victoribarra.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;


    public BaseDatos(@Nullable Context context) {
        super(context,ConstanteBaseDatos.DATABASE_NAME,null,ConstanteBaseDatos.DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstanteBaseDatos.TABLE_MASCOTAS + "(" +
                                        ConstanteBaseDatos.TABLE_MASCOTAS_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE    + " TEXT," +
                                        ConstanteBaseDatos.TABLE_MASCOTAS_FOTO      + " INTEGER" +
                                        ")";

        String queryCrearTablaLikes = "CREATE TABLE " + ConstanteBaseDatos.TABLE_LIKES + "(" +
                ConstanteBaseDatos.TABLE_LIKES_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstanteBaseDatos.TABLE_LIKES_ID_MASCOTA    + " INTEGER, " +
                ConstanteBaseDatos.TABLE_LIKES_TOTAL + " INTEGER, " +
                "FOREIGN KEY (" + ConstanteBaseDatos.TABLE_LIKES_ID_MASCOTA + ") " +
                "REFERENCES " + ConstanteBaseDatos.TABLE_MASCOTAS + "("+ConstanteBaseDatos.TABLE_MASCOTAS_ID+")" +
                ")";

        String queryCrearTablaFavoritos = "CREATE TABLE " + ConstanteBaseDatos.TABLE_FAVORITOS + " ( " +
                ConstanteBaseDatos.TABLE_FAVORITOS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_NOMBRE    + " TEXT," +
                ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_FOTO      + " INTEGER, " +
                ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_LIKES      + " INTEGER, " +
                ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_CAPTION     + " INTEGER " +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikes);
        db.execSQL(queryCrearTablaFavoritos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstanteBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstanteBaseDatos.TABLE_LIKES);
        onCreate(db);

    }

    public ArrayList<Mascota> obtenerTodasLasMascotas (){
        ArrayList<Mascota> mascotas= new ArrayList<>();

        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
       Cursor registros = db.rawQuery(query,null);

       while(registros.moveToNext()){
           Mascota mascotaActual = new Mascota();
           mascotaActual.setId(registros.getString(0));
           mascotaActual.setUsername(registros.getString(1));
           mascotaActual.setUrlfoto(registros.getString(2));


           mascotas.add(mascotaActual);

       }
       db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_MASCOTAS,null,contentValues);
        db.close();
    }

    public void insertarLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_LIKES,null,contentValues);
        db.close();
    }
    public  int obtenerLike ( Mascota mascota) {
        int likes =0;

        String query = "SELECT COUNT ("+ConstanteBaseDatos.TABLE_LIKES_TOTAL+")" +
                " FROM " + ConstanteBaseDatos.TABLE_LIKES +
                " WHERE " + ConstanteBaseDatos.TABLE_LIKES_ID_MASCOTA + "=" + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);

        }
        db.close();

        return  likes;
    }

    public ArrayList<Mascota> obtenertodosFavoritos (){
        ArrayList<Mascota> mascotas= new ArrayList<>();

        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_FAVORITOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getString(0));
            mascotaActual.setUsername(registros.getString(1));
            mascotaActual.setUrlfoto(registros.getString(2));
            mascotaActual.setLikes(registros.getInt(3));

            mascotas.add(mascotaActual);

        }
        db.close();
        return mascotas;
    }



    public void insertarFavorito(ContentValues contentValues,int i, int j){

        SQLiteDatabase db = this.getWritableDatabase();
        if (i <=5) {

            db.insert(ConstanteBaseDatos.TABLE_FAVORITOS, null, contentValues);
            Toast.makeText(context,String.valueOf(i),Toast.LENGTH_SHORT).show();
        }
        else {
            db.update(ConstanteBaseDatos.TABLE_FAVORITOS,contentValues,ConstanteBaseDatos.TABLE_FAVORITOS_ID + " = " + String.valueOf(j),null);
            Toast.makeText(context,String.valueOf(j),Toast.LENGTH_SHORT).show();
        }

        db.close();

    }
}
