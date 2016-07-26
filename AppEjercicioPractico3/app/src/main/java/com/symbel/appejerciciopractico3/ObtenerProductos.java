package com.symbel.appejerciciopractico3;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.symbel.appejerciciopractico3.activity.MainActivity;
import com.symbel.appejerciciopractico3.adapter.ListadoAdapter;
import com.symbel.appejerciciopractico3.fragment.ListadoFragment;
import com.symbel.appejerciciopractico3.model.Producto;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by estefi on 12/07/2016.
 */
public class ObtenerProductos extends AsyncTask<Void, Void, ArrayList<Producto>> {

    private Context mContext;
    public ObtenerProductos(Context context){
        this.mContext=context;
    }


    protected ProgressDialog progressDialog;

    public static ArrayList<Producto> lista_productos = null;

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Log.d(getClass().getCanonicalName(), "Executando onPreExecute de EfetuaLogin");
        //inicia diálogo de progresso, mostranto processamento com servidor.
        progressDialog = ProgressDialog.show(mContext, "Conectando con el Servidor", "Espera unos segundos.", true, false);
    }


    protected ArrayList<Producto> doInBackground(Void... params){


        URL dir_obtener_productos = null;
        HttpURLConnection httpURLConnection = null;

        try{
            dir_obtener_productos = new URL(Constantes.URL_SERVICIO_PRODUCTOS);
            httpURLConnection = (HttpURLConnection)dir_obtener_productos.openConnection();
            httpURLConnection.setRequestMethod("GET");//Vamos a enviar informacion en el cuerpo

            int code_resp = httpURLConnection.getResponseCode();

            if (code_resp == HttpURLConnection.HTTP_OK)
            {
                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String json_personas = br.readLine();

                Gson gson = new Gson();

                lista_productos = gson.fromJson (json_personas, new TypeToken<ArrayList<Producto>>(){}.getType());
                Log.d(getClass().getCanonicalName(), lista_productos.toString());


            }

            httpURLConnection.disconnect();



        }catch (Throwable t){
            Log.d(getClass().getCanonicalName(), "Error al obtener Productos", t);
        }
        return lista_productos;
    }

    @Override
    protected void onPostExecute(ArrayList<Producto> listado_productos)
    {
        super.onPostExecute(listado_productos);
        ListadoAdapter adapter = new ListadoAdapter(mContext,listado_productos );
        ListadoFragment.rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        ListadoFragment.rv.setLayoutManager(llm);
        ListadoFragment.rv.setHasFixedSize(true);


        progressDialog.dismiss();
    }



}
