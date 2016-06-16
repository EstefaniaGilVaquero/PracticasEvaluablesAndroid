package com.symbel.appejerciciopractico1;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.HashMap;


public class DisplayImages extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context;

    Integer contadorImagenes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        //Relleno spinner de galerias
        Spinner spGallery = (Spinner) findViewById(R.id.spinnerGallery);

        //Cargo los arrays de imagenes
        Resources resources = getResources();
        TypedArray biciArray = resources.obtainTypedArray(R.array.bicis);
        TypedArray biciPatines = resources.obtainTypedArray(R.array.patines);

        ArrayAdapter adapter = ArrayAdapter.createFromResource( this, R.array.galerias , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGallery.setAdapter(adapter);

        spGallery.setOnItemSelectedListener(this);

        //Listener
        View.OnClickListener objetoEscuchador = new escuchaEventos(this);

        //CAPTURO EL BOTÓN Y LE ASOCIO EL LISTENER
        Button botonSI = (Button)findViewById(R.id.siBTN);
        botonSI.setOnClickListener(objetoEscuchador);
        Button botonNO = (Button)findViewById(R.id.noBTN);
        botonNO.setOnClickListener(objetoEscuchador);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (parent.getId()) {
            case R.id.spinnerGallery:

                TypedArray arrayBicis = getResources().obtainTypedArray(R.array.bicis);
                TypedArray arrayPatines = getResources().obtainTypedArray(R.array.patines);

                ImageView imagenGaleria = (ImageView) findViewById(R.id.galleryView);

                String galSelec = parent.getSelectedItem().toString();
                if (galSelec.equalsIgnoreCase("bicis")){
                    Drawable drawable = arrayBicis.getDrawable(0);
                    imagenGaleria.setImageDrawable(drawable);
                  //  imagenGaleria.setImageResource(arrayBicis.getResourceId(0,0));
                }else if (galSelec.equalsIgnoreCase("patines")){
                    imagenGaleria.setImageResource(arrayPatines.getResourceId(0,0));
                }

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

//      //  Activity a = (Activity) context;
//        ImageView imagenBici = (ImageView) findViewById(R.id.bicisView);
//
//        if ( contadorImagenes < biciArray.length-1){
//            contadorImagenes++;
//
//        }else{
//            contadorImagenes = 0;
//        }
//
//        imagenBici.setImageResource(biciArray[contadorImagenes]);
    }

}
