package com.victoribarra.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.victoribarra.petagram.R;
import com.victoribarra.petagram.favoritos;
import com.victoribarra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){

      /* ArrayList<Mascota> mascotas= new ArrayList<Mascota>();

        mascotas.add(new Mascota("perro1",R.drawable.perro1,10));
        mascotas.add(new Mascota("perro2",R.drawable.perro2,10));
        mascotas.add(new Mascota("perro3",R.drawable.perro3,10));
        mascotas.add(new Mascota("perro4",R.drawable.perro4,10));
        mascotas.add(new Mascota("perro5",R.drawable.perro5,10));
        mascotas.add(new Mascota("perro6",R.drawable.perro6,10));
        mascotas.add(new Mascota("perro7",R.drawable.perro7,10));

        return mascotas;
*/
      BaseDatos db = new BaseDatos(context);
      insertar3Mascotas(db);
      return db.obtenerTodasLasMascotas();

    }

    public void insertar3Mascotas (BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "perro1");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro1);


        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "perro2");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro2);

        db.insertarMascota(contentValues);
        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "perro3");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro3);

        db.insertarMascota(contentValues);


    }
    public void  darLikeMascota (Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_ID_MASCOTA,mascota.getId());
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_TOTAL,1);
        db.insertarLike(contentValues);
    }

    public int obtenerLikeMascota (Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerLike(mascota);
    }

    public ArrayList<Mascota> obtenerFavoritos(){

        BaseDatos db = new BaseDatos(context);
        return db.obtenertodosFavoritos();

    }

    public void insertar1favorito (Mascota mascota,int i, int j){
        BaseDatos db= new BaseDatos(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_NOMBRE, mascota.getNombre());
        contentValues.put(ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_FOTO, mascota.getFoto());
        contentValues.put(ConstanteBaseDatos.TABLE_FAVORITOS_MASCOTA_LIKES,obtenerLikeMascota(mascota));


        db.insertarFavorito(contentValues,i,j);

    }
}
