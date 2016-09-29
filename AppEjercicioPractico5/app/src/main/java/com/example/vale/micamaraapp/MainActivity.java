package com.example.vale.micamaraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_ACTIVIDAD = 100;
    private String ruta_captura_foto_actual;
    private static final String SUFIJO_FOTO = ".jpg";
    private static final String PREFIJO_FOTO = "VALE_PIC_";
  //  List<Bitmap> lista = new ArrayList<Bitmap>();
    private ListView listView;
    public List<String> lista_rutas;

    public final static int NVECES_FOTO = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_rutas = new ArrayList<String>(NVECES_FOTO);

        do
        {
            tomarFoto();
            j = j + 1;
        }while (j<NVECES_FOTO);

        //Listeners para las imagenes
        //Listener
        View.OnClickListener objetoEscuchador = new CompartirFoto(this);

        //CAPTURO EL BOTÓN Y LE ASOCIO EL LISTENER
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(objetoEscuchador);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(objetoEscuchador);
//        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
//        imageView3.setOnClickListener(objetoEscuchador);
    }

    private void tomarFoto(){
        //  String str_dev = null;
        Intent intent_foto = null;
        Uri uri_ruta = null;

        Log.d(getClass().getCanonicalName(), "Tomando Foto");

        intent_foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.ruta_captura_foto_actual = obtenerRutaFichero();
        lista_rutas.add(ruta_captura_foto_actual);
        uri_ruta = obtenerUriFromRuta(ruta_captura_foto_actual);

        intent_foto.putExtra(MediaStore.EXTRA_OUTPUT, uri_ruta);
        startActivityForResult(intent_foto, CODIGO_ACTIVIDAD);
    }

    private Uri obtenerUriFromRuta(String ruta){
        Uri uri_dev = null;
        File f = null;

        f = new File(ruta);

        try
        {
            if (f.createNewFile())
                Log.d(getClass().getCanonicalName(), "Fichero creado");
            else
                Log.d(getClass().getCanonicalName(), "Fichero NO creado (ya existía)");
        }
        catch (IOException e)
        {
            Log.e(getClass().getCanonicalName(), "Error creando el fichero", e);
        }

        uri_dev = Uri.fromFile(f);

        Log.d(getClass().getCanonicalName(), "URI FOTO = " + uri_dev.toString());

        return uri_dev;
    }



    private String obtenerRutaFichero(){

        String nombreFoto = null;
        String momento_actual = null;

        momento_actual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //así nos garantizamos emplear un sufijo aleatorio: el nombre del archivo de la imagen incluirá el momento exacto
        nombreFoto = PREFIJO_FOTO + momento_actual + SUFIJO_FOTO;
        nombreFoto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/"+nombreFoto;

        Log.d(getClass().getCanonicalName(), "RUTA FOTO = " + nombreFoto);

        return nombreFoto;
    }





    public Bitmap getBitMapFromFile(String ruta)
    {
        Bitmap bitmap = null;

            File imgFile = new File(ruta);
            bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        return bitmap;
    }

    private void mostrarFotos()
    {
        Log.d(getClass().getCanonicalName(), "Mostrando fotos");

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setImageBitmap(getBitMapFromFile(lista_rutas.get(0)));

       // ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getCanonicalName(), "Ha tocado foto 1"); //requestCode == CODIGO_ACTIVIDAD

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);

                Uri uri = Uri.fromFile(new File(lista_rutas.get(0)));
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "ENVIAR FOTO ... "));
            }
        });


        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setImageBitmap(getBitMapFromFile(lista_rutas.get(1)));

//        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
//        imageView3.setImageBitmap(getBitMapFromFile(lista_rutas.get(2)));
    }

    int j = 0;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(getClass().getCanonicalName(), "VUELVE DE hacer la FOTO"); //requestCode == CODIGO_ACTIVIDAD

        switch (resultCode)
        {
            case RESULT_OK:

                    Log.d(getClass().getCanonicalName(), "La cosa fue bien Código " + resultCode);

                    if (data == null)//el fichero ha sido guarado en una ruta => se ha usado el putExtra MediaStore.EXTRA_OUTPUT
                    {
                        Log.d(getClass().getCanonicalName(), "Se empleó el parámetro MediaStore.EXTRA_OUTPUT");

                        try
                        {
                            //lista_rutas.add(ruta_captura_foto_actual);
                            if (j == NVECES_FOTO)
                            {
                                mostrarFotos();
                            }
                        } catch (Exception e)
                        {
                            Log.e(getClass().getCanonicalName(), "ERRORAZO recuperadno la foto tomada" , e);
                        }
                    }
                    else
                    { //la foto ha sido capturada y devuelta en un intent = NO se ha usado el putExtra MediaStore.EXTRA_OUTPUT
                        Log.d(getClass().getCanonicalName(), "NO Se empleó el parámetro MediaStore.EXTRA_OUTPUT : se devolvió el bitmap");
                    }

                break;

            case RESULT_CANCELED:
                    Log.d(getClass().getCanonicalName(), "La cosa se canceló " + resultCode);
                break;

            default:
                Log.d(getClass().getCanonicalName(), "FIN CON CÓDIGO " + resultCode);
        }
    }



















}
