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

    public void insertarUsuario (Usuarios usuarios)
    {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.execSQL("INSERT INTO USUARIOS (ID, USUARIO, PASSWORD ) VALUES ('"+usuarios.getId()+"' , '"+usuarios.getUsuario()+"', '"+usuarios.getPassword()+"')");
        this.cerrarBaseDatos(db);
    }

    public boolean validarUsuarioPassword(String usuario, int password){
        boolean usuarioOK = false;

        String consulta = "SELECT COUNT(*) FROM USUARIOS WHERE USUARIO ='"+usuario+"' AND PASSWORD = "+ password;

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

    public void deleteUsuarioImagen(String usuario, int idImagen){
        SQLiteDatabase db = baseDatos.getWritableDatabase();


      ContentValues valores = new ContentValues();
      valores.put("USUARIO", usuario);
      valores.put("IMAGEN", idImagen);

        // Insertar usuario e idImagen
      db.delete("FAVORITOS","USUARIO=? and IMAGEN=?",new String[]{usuario,String.valueOf(idImagen)});
    }

    public void insertarFavorito(String usuario, int idImagen){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        //TODO: controlar exceptiones de BD

            ContentValues valores = new ContentValues();
            valores.put("USUARIO", usuario);
            valores.put("IMAGEN", idImagen);

            // Insertar usuario e idImagen
            db.insertOrThrow("FAVORITOS", null, valores);
    }

    public boolean tableIsEmpty(String tableName){

        boolean tablaVacia = false;
        int numReg = 0;

        String consulta = "SELECT COUNT(*) FROM "+tableName;
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


        String consulta = "SELECT * FROM "+nombreTabla;

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


//    public String insertarCabeceraPedido(CabeceraPedido pedido) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        // Generar Pk
//        String idCabeceraPedido = CabecerasPedido.generarIdCabeceraPedido();
//
//        ContentValues valores = new ContentValues();
//        valores.put(CabecerasPedido.ID_CABECERA_PEDIDO, idCabeceraPedido);
//        valores.put(CabecerasPedido.FECHA, pedido.fecha);
//        valores.put(CabecerasPedido.ID_CLIENTE, pedido.idCliente);
//        valores.put(CabecerasPedido.ID_FORMA_PAGO, pedido.idFormaPago);
//
//        // Insertar cabecera
//        db.insertOrThrow(Tablas.CABECERA_PEDIDO, null, valores);
//
//        return idCabeceraPedido;
//    }




}
