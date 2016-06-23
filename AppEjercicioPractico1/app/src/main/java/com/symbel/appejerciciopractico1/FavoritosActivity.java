package com.symbel.appejerciciopractico1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.symbel.appejerciciopractico1.dao.OperacionesBaseDatos;


import java.util.List;

public class FavoritosActivity extends Activity {

    Context context;
    //creo el objeto de la base de datos

    ImageView imageView;

    //Array de favoritos
    List<Integer> arrayFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        imageView = (ImageView) findViewById(R.id.image1);
        TextView nombreET = (TextView)findViewById(R.id.nombreET);

        //Listener
        View.OnClickListener objetoEscuchador = new EscuchaEventos(this);

        //CAPTURO EL BOTÓN Y LE ASOCIO EL LISTENER
        Button buttonVOLVER = (Button)findViewById(R.id.volverBTN);
        buttonVOLVER.setOnClickListener(objetoEscuchador);

        //Obtenco el usuario activo
        String usuario = UsuarioActivo.getUsuarioactivo();

        //Pongo el usuario en el TextView
        nombreET.setText(usuario);

        //Obtengo favoritos del usuarioActivo
        OperacionesBaseDatos datos = OperacionesBaseDatos.obtenerInstancia(this.context);
        arrayFavoritos = datos.obtenerFavoritos(usuario);

        //Si el array tiene imagenes cargo el imageView con la primera de ellas
        if (!arrayFavoritos.isEmpty()){
            imageView.setImageResource(arrayFavoritos.get(0));
        }

        // Note that Gallery view is deprecated in Android 4.1---
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position,long id)
            {
                // Mostrar imagen seleccionada
                //Recorremos el cursor de la bd con los favoritos
                imageView.setImageResource(arrayFavoritos.get(position));
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return arrayFavoritos.size();
        }
        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(arrayFavoritos.get(position));
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }
}



