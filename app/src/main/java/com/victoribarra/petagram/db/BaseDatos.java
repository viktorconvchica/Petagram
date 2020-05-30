package com.victoribarra.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikes);

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
           mascotaActual.setId(registros.getInt(0));
           mascotaActual.setNombre(registros.getString(1));
           mascotaActual.setFoto(registros.getInt(2));

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
}