package com.symbel.appejerciciopractico1;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import com.symbel.appejerciciopractico1.dao.OperacionesBaseDatos;


import java.util.List;

public class FavoritosActivity extends Activity {

    Context context;
    //creo el objeto de la base de datos
   OperacionesBaseDatos datos;
    List<Integer> favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        //Obtengo el usuario del intent que lanza la actividad
//        String usuario = getIntent().getStringExtra("Usuario");
//        TextView nombreUsuario = (TextView) findViewById(R.id.nombreET);
//        nombreUsuario.setText(usuario);

        //Listener
        View.OnClickListener objetoEscuchador = new escuchaEventos(this);

        //CAPTURO EL BOTÓN Y LE ASOCIO EL LISTENER
        Button buttonVOLVER = (Button)findViewById(R.id.volverBTN);
        buttonVOLVER.setOnClickListener(objetoEscuchador);

        datos = OperacionesBaseDatos.obtenerInstancia(this.context);

        favoritos = datos.obtenerFavoritos("Estefi");

        // Note that Gallery view is deprecated in Android 4.1---
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position,long id)
            {
                // display the images selected
                ImageView imageView = (ImageView) findViewById(R.id.image1);

                //Recorremos el cursor de la bd con los favoritos


                imageView.setImageResource(favoritos.get(position));
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
            return favoritos.size();
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
            imageView.setImageResource(favoritos.get(position));
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }
}



