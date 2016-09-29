package com.example.vale.micamaraapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.util.Log;
import android.view.View;


import java.io.File;

/**
 * Created by estefi on 09/06/2016.
 */
public class CompartirFoto implements View.OnClickListener {

    Context context;

    public CompartirFoto(Context context){this.context = context;}

 @Override
    public void onClick(View vista_seleccioanda)
    {


        MainActivity mMainActivity = (MainActivity) this.context;


        //obtengo el Id de la vista
        int id_vista_seleccionada = vista_seleccioanda.getId();

        Log.d(getClass().getCanonicalName(), "Ha pulsado una imagen");


        switch (id_vista_seleccionada)
        {
            case R.id.imageView1:

                Log.d(getClass().getCanonicalName(), "Ha tocado foto 1");
                compartirFoto(mMainActivity.lista_rutas.get(0));

                break;

            case R.id.imageView2:
                Log.d(getClass().getCanonicalName(), "Ha tocado foto 1");
                compartirFoto(mMainActivity.lista_rutas.get(1));

                break;
        }
    }


    private void compartirFoto(String ruta){
        Activity a = (Activity) context;

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        Uri uri = Uri.fromFile(new File(ruta));
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        a.startActivity(Intent.createChooser(shareIntent, "ENVIAR FOTO ... "));

    }

}