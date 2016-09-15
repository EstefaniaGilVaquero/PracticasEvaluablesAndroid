package com.symbel.appejerciciopractico4;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.symbel.appejerciciopractico4.model.Recados;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by estefi on 13/09/2016.
 */
public class DescargaTareas extends AsyncTask<String,Void,ArrayList<Recados>> {

    protected ArrayList<Recados> doInBackground(String... params)
    {
        ArrayList<Recados> l_recados = null;

        Log.d(getClass().getCanonicalName(), "AsynkTask descargaTareas iniciado");

        //Http a la url proporcionada
        URL dirTareas = null ;
        HttpURLConnection httpURLConnection = null;
        InputStream input = null;

        try{
            dirTareas = new URL(params[0]);
            httpURLConnection = (HttpURLConnection)dirTareas.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int code_resp = httpURLConnection.getResponseCode();

            //Si la conexion es OK
            if (code_resp == HttpURLConnection.HTTP_OK)
            {
                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //Leemos el json
                String json_recados = br.readLine();

                Gson gson = new Gson();
                l_recados = gson.fromJson (json_recados, new TypeToken<ArrayList<Recados>>(){}.getType());
            }

            httpURLConnection.disconnect();
            Log.d(getClass().getCanonicalName(), "Descarga correcta del listado de recados");

        }catch (Throwable t)
        {
            Log.d(getClass().getCanonicalName(), "Error al descargar lista de recados", t);
        }

        //En cualquier caso liberamos recursos
        finally
        {
            httpURLConnection.disconnect();
        }

        return l_recados;
    }



}
