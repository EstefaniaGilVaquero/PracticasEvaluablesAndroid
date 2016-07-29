package com.symbel.appejerciciopractico3.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.symbel.appejerciciopractico3.adapter.HistoricoAdapter;
import com.symbel.appejerciciopractico3.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by vale on 20/06/16.
 */
public class HistoricoFragment extends Fragment {


    public static RecyclerView rv;
    public static ArrayList<Producto> historico_productos = new ArrayList();
    public final static String HISTORICO_PRODUCTOS = "historico";//nombre del fichero de preferences perfil.xml será

    public HistoricoFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getActivity();


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_historico, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);

        historico_productos = cargarHistorico(context);

        //Asocio el adaptador al recyclerView
        HistoricoAdapter adapter = new HistoricoAdapter(context, historico_productos);
        HistoricoFragment.rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        HistoricoFragment.rv.setLayoutManager(llm);
        HistoricoFragment.rv.setHasFixedSize(true);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //LLamo a la carga del historico cada vez que se muestra el tab Historico,
        // para que se refresquen los cards
        cargarHistorico(getActivity());

    }

    public static ArrayList<Producto> cargarHistorico(Context context) {

        //Reseteo el historico
        historico_productos = new ArrayList();

        //Recupero los productos visitados del sharedPreferences
       // ArrayList<Producto> historico_productos = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(HISTORICO_PRODUCTOS,Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        Producto producto = null;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            //El nombre del producto y la imagen vienen concatenados en el value del sharedPreferences
            String[] result = entry.getValue().toString().split(";");
            //Obtenemos la imagen del servidor
            Bitmap imagencita = null;
            try {
                String url = result[1];
                imagencita = new DescargarImagenes(result[1]).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            producto = new Producto(result[0],imagencita,entry.getKey());

            historico_productos.add(producto);
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());

        }

        return historico_productos;
    }
}
