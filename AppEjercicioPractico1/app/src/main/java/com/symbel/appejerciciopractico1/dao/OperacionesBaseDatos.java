package com.symbel.appejerciciopractico1.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.symbel.appejerciciopractico1.dto.Favoritos;
import com.symbel.appejerciciopractico1.dto.Usuarios;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by estefi on 22/06/2016.
 */
public final class OperacionesBaseDatos {

    private static BaseDatosGaleria baseDatos;

    //Patron singleton
    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();

    private OperacionesBaseDatos() {
    }

    public static OperacionesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosGaleria(contexto);
        }
        return instancia;
    }

    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }

    public void insertarUsuario (Usuarios usuario)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        //TODO: controlar exceptiones de BD

        ContentValues valores = new ContentValues();
        valores.put("USUARIO", usuario.getUsuario());
        valores.put("PASSWORD", usuario.getPassword());

        // Insertar usuario y password
        db.insertOrThrow("USUARIOS", null, valores);

        this.cerrarBaseDatos(db);
    }

    public boolean validarUsuarioPassword(Usuarios usuario){
        boolean usuarioOK = false;

        String consulta = "SELECT COUNT(*) FROM USUARIOS WHERE USUARIO ='"+usuario.getUsuario()+"' AND PASSWORD = "+ usuario.getPassword();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        //Si ha devuelto registros es que el usuario esta dado de alta.
        if( cursor != null || cursor.getCount() <=0){
            cursor.moveToFirst();
            if (cursor.getInt(0) >= 1){
                usuarioOK = true;
            }
            cursor.close();
        }
        this.cerrarBaseDatos(db);
        return usuarioOK;
    }

    public void borrarFavorito(Favoritos favoritos){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("USUARIO", favoritos.getUsuario());
        valores.put("IMAGEN", favoritos.getIdImage());

        //TODO: Usar valores
        // Insertar usuario e idImagen
        db.delete("FAVORITOS","USUARIO=? and IMAGEN=?",new String[]{favoritos.getUsuario(),String.valueOf(favoritos.getIdImage())});
    }

    public void insertarFavorito(Favoritos favoritos){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        //TODO: controlar exceptiones de BD
        ContentValues valores = new ContentValues();
        valores.put("USUARIO", favoritos.getUsuario());
        valores.put("IMAGEN", favoritos.getIdImage());

        // Insertar usuario e idImagen
        db.insertOrThrow("FAVORITOS", null, valores);
    }

    public boolean tableIsEmpty(String nombreTabla){

        boolean tablaVacia = false;

        String consulta = "SELECT COUNT(*) FROM " + nombreTabla;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);
        if( cursor != null || cursor.getCount() <=0){
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0){
                tablaVacia = true;
            }
            cursor.close();
        }
        return tablaVacia;
    }

    public void mostrarContenidoTabla(String nombreTabla){

        String consulta = "SELECT * FROM " + nombreTabla;

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        //Si hay registros los pinto en el log
        if( cursor != null || cursor.getCount() <=0){
            if (cursor.moveToFirst()) {
                Log.v("BD", "_______________________________________");
                Log.v("BD", "TABLA "+nombreTabla);
                do {
                    StringBuilder sb = new StringBuilder();
                    int columnsQty = cursor.getColumnCount();
                    for (int idx = 0; idx < columnsQty; ++idx) {
                        sb.append(cursor.getString(idx));
                        if (idx < columnsQty - 1)
                            sb.append("; ");
                    }

                    Log.v("BD", String.format("Row: %d, Values: %s", cursor.getPosition(), sb.toString()));
                }while (cursor.moveToNext()) ;
                Log.v("BD", "_______________________________________");
            }
            cursor.close();
        }
        this.cerrarBaseDatos(db);
    }

    public List<Integer> obtenerFavoritos(String usuario){

        String consulta = "SELECT * FROM FAVORITOS WHERE USUARIO ='"+usuario+"'";

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);
        List<Integer> favoritos = new ArrayList<Integer>();
        while(cursor.moveToNext()){
            Integer idImagen = cursor.getInt(cursor.getColumnIndex("IMAGEN"));
            favoritos.add(idImagen);
        }
        return favoritos;
    }
}
