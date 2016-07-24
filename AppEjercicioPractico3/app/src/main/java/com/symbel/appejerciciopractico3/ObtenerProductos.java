package com.symbel.appejerciciopractico3;


import android.os.AsyncTask;
import android.util.Log;

import com.symbel.appejerciciopractico3.model.Producto;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by estefi on 12/07/2016.
 */
public class ObtenerProductos extends AsyncTask<String, Void, Producto> {

    protected Producto doInBackground(String... params){

//        private Integer id;
//        private Image imagen;
//        private String nombre;
//        private Double precio;
//        private Integer unidades;
//        private String descripcion;
        Producto producto = null;
        int id_producto;
        String nombre_producto = null;
        Double precio_producto;
        int unidades_producto;
        String descripcion_producto = null;

        URL dir_obtener_productos = null;
        HttpURLConnection httpURLConnection = null;

        try{
            dir_obtener_productos = new URL(Constantes.URL_SERVICIO_PRODUCTOS);
            httpURLConnection = (HttpURLConnection)dir_obtener_productos.openConnection();
            httpURLConnection.setRequestMethod("POST");//Vamos a enviar informacion en el cuerpo


            OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream());
            nombre_producto = params[1];

            osw.write(nombre_producto);
            osw.close();

            int resp_code = httpURLConnection.getResponseCode();

            if (resp_code == HttpURLConnection.HTTP_CREATED);
            producto = null;

            httpURLConnection.disconnect();


        }catch (Throwable t){
            Log.d(getClass().getCanonicalName(), "Error al obtener Productos", t);
        }



        return producto;

    }
}
