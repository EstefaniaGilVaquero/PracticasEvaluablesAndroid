package com.symbel.appejerciciopractico3.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.symbel.appejerciciopractico3.DescargarImagenes;
import com.symbel.appejerciciopractico3.ObtenerProductos;
import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.adapter.ListadoAdapter;
import com.symbel.appejerciciopractico3.model.Producto;
import com.symbel.appejerciciopractico3.utils.UtilInternet;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by estefi on 21/07/2016.
 */
public class ListadoFragment extends Fragment {
    //DATA SOURCE

    public static RecyclerView rv;
    public static ArrayList<Producto> listado_productos;
    public static HashMap<Integer,Bitmap> imagenes_productos = new HashMap<>();

    //Llamo al constructor del padre. Necesario para iniciar el fragment
    public ListadoFragment() {
        super();
    }

    //Sobreescribo este método para devolver la vista raíz del fragment
    //inflando para ello el layout que representa la vista de dicho fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_listado, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);

        if (UtilInternet.isNetworkAvailable(getActivity())) {
            Log.d(getClass().getCanonicalName(), "SI HAY INTERNET");
            try {
              listado_productos = new ObtenerProductos(context).execute().get();
                Bitmap imagenActual = null;
                for (int i = 0; i < listado_productos.size(); i++){
                    String url = listado_productos.get(i).getImagen();
                    //String url = "http://www.highhdwallpapers.com/wp-content/uploads/2016/02/lakes-hd-wallpapers-awesome-beautiful-hd-high-quality-nature-desktop-widescreen-backgrounds.jpg";
                    listado_productos.get(i).setImagenBitMap(new DescargarImagenes(url).execute().get());
                    //imagenActual = new DescargarImagenes(url).execute().get();
                   // imagenes_productos.put(Integer.parseInt(listado_productos.get(i).getId()),imagenActual);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } else {
            Log.d(getClass().getCanonicalName(), "NO HAY INTERNET");

        }

        ListadoAdapter adapter = new ListadoAdapter(context,listado_productos);
        ListadoFragment.rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        ListadoFragment.rv.setLayoutManager(llm);
        ListadoFragment.rv.setHasFixedSize(true);

        return rootView;
    }

}