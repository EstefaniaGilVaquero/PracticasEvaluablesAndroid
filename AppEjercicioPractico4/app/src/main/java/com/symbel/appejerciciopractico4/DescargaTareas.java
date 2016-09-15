package com.symbel.appejerciciopractico4;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.symbel.appejerciciopractico4.model.Recados;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by estefi on 13/09/2016.
 */
public class DescargaTareas extends AsyncTask<String,Void,List<String[]>> {



    protected List<String[]> doInBackground(String... params)
    {
      //  List<Recados> l_recados = null;

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

                //TODO: No se como hacerlo
                String json_recados = br.readLine();

                Gson gson = new Gson();
         //     List<Recados> recados = gson.fromJson (json_recados, Recados.class);
               // l_recados = gson.fromJson (json_recados, new TypeToken<ArrayList<Recados>>(){}.getType());

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

       /* //TODO: De momento lo relleno a mano para poder implementar el cardView

        *//*[{"fecha_hora":"Sep 15, 2016 6:46:50 AM","nombre_cliente":"Anguita","telefono":"659887433","direccion_recogida":"Calle del Buenismo","direccion_entrega":"Registro Propiedad 2","descripcion":"Obtener nota simple del registro de la propiedad","fecha_hora_maxima":"Sep 15, 2016 7:16:50 AM"},
        {"fecha_hora":"Sep 15, 2016 4:16:50 AM","nombre_cliente":"Juanito","telefono":"644121140","direccion_recogida":"Calle Manuela Karmena, 5","direccion_entrega":"Ministerio Sanidad","descripcion":"LLevar los resultados de los análisis","fecha_hora_maxima":"Sep 15, 2016 4:46:50 AM"},
        {"fecha_hora":"Sep 15, 2016 5:16:50 AM","nombre_cliente":"Pepita","telefono":"659885025","direccion_recogida":"Calle La Concordia, 5","direccion_entrega":"Servicio de empleo","descripcion":"Ve a sellar el paro a mi hijo","fecha_hora_maxima":"Sep 15, 2016 5:46:50 AM"},
        {"fecha_hora":"Sep 15, 2016 6:16:50 AM","nombre_cliente":"Domingo","telefono":"666897485","direccion_recogida":"Mercado Maravillas","direccion_entrega":"Calle Londres, 5","descripcion":"Traeme lo de la casqueria que encargue","fecha_hora_maxima":"Sep 15, 2016 6:46:50 AM"}]
*//*
        List<String[]> l_tareas = new ArrayList<String[]>();

        l_tareas.add(new String[]{"Sep 15, 2016 6:46:50 AM","Anguita","659887433","Calle del Buenismo","Registro Propiedad 2","Obtener nota simple del registro de la propiedad","Sep 15, 2016 7:16:50 AM"});*/
        List<String[]> l_recados = new ArrayList<String[]>();
        l_recados.add(new String[]{"Sep 15, 2016 6:46:50 AM","Anguita","659887433","Calle del Buenismo",
                "Registro Propiedad 2","Obtener nota simple del registro de la propiedad","Sep 15, 2016 7:16:50 AM"});
        return l_recados;
    }



}
