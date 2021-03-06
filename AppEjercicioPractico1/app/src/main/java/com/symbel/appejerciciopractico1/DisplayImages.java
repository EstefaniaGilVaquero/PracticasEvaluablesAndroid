package com.symbel.appejerciciopractico1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Spinner;


public class DisplayImages extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context;

    Spinner spGallery;

    //el array seleccionado en cada momento
    static TypedArray arraySelec = null;

    //id de la imagen mostrada en imageView
    static int idImagen;
    static String usuario;

    Drawable drawable;

    //indice para recorrer el array de imagenes
    static Integer contadorImagenes = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        //Relleno spinner de galerias
        spGallery = (Spinner) findViewById(R.id.spinnerGallery);

        ArrayAdapter adapter = ArrayAdapter.createFromResource( this, R.array.galerias , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGallery.setAdapter(adapter);
        spGallery.setOnItemSelectedListener(this);

        //Listener
        View.OnClickListener objetoEscuchador = new EscuchaEventos(this);

        //CAPTURO EL BOTÓN Y LE ASOCIO EL LISTENER
        Button botonSI = (Button)findViewById(R.id.siBTN);
        botonSI.setOnClickListener(objetoEscuchador);
        Button botonNO = (Button)findViewById(R.id.noBTN);
        botonNO.setOnClickListener(objetoEscuchador);
        Button botonFAV = (Button)findViewById(R.id.favoritosBTN);
        botonFAV.setOnClickListener(objetoEscuchador);

        //Pongo el nombre del usuario en el boton de favoritos
        botonFAV.setText(UsuarioActivo.getUsuarioactivo());


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (parent.getId()) {
            case R.id.spinnerGallery:

                ImageView imagenGaleria = (ImageView) findViewById(R.id.galleryView);

                //Guardamos que array de imagenes se ha cargado en una variable de clase
                this.setArraySelec();

                //Reseteamos el contador de imagenes a 0
                contadorImagenes = 0;

                //Cargamos la primera imagen del array en el imageView
                drawable = arraySelec.getDrawable(0);
                imagenGaleria.setImageDrawable(drawable);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Callback method to be invoked when the selection disappears from this
        // view. The selection can disappear for instance when touch is
        // activated or when the adapter becomes empty.
    }

    public void nextImgae(){

      //  Activity a = (Activity) context;

        ImageView imagenGaleria = (ImageView) findViewById(R.id.galleryView);

        //TODO: Ojo a la chapuza del siglo
        //Guardo el id de la imagen actual
        String nombreFoto = arraySelec.getString(contadorImagenes);
        nombreFoto = nombreFoto.replaceAll("res/drawable/", "");
        nombreFoto = nombreFoto.substring(0,nombreFoto.length()-4);
        Log.d("imagen", "nombre de la imagen: " + nombreFoto);
        idImagen = getResources().getIdentifier(nombreFoto , "drawable", getPackageName());
        Log.d("imagen", "id de la imagen: " + idImagen);

        if ( contadorImagenes < arraySelec.length()-1){
            contadorImagenes++;

        }else{
            contadorImagenes = 0;
        }

        drawable = arraySelec.getDrawable(contadorImagenes);
        imagenGaleria.setImageDrawable(drawable);
    }

    public void setArraySelec(){
        TypedArray arrayBicis = getResources().obtainTypedArray(R.array.bicis);
        TypedArray arrayPatines = getResources().obtainTypedArray(R.array.patines);
        int galSelec = spGallery.getSelectedItemPosition();

        switch (galSelec) {
            //Esta cargado el array de bicis
            case 0:
                Log.d(getClass().getCanonicalName(), "Ha seleccionado galeria Bicis");
                arraySelec = arrayBicis;
                break;

            //Esta cargado el array de patines
            case 1:
                Log.d(getClass().getCanonicalName(), "Ha seleccionado galeria Patines");
                arraySelec = arrayPatines;
                break;

        }
    }

}
