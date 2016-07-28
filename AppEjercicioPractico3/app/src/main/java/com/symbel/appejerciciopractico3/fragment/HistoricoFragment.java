package com.symbel.appejerciciopractico3.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.symbel.appejerciciopractico3.ObtenerProductos;
import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.adapter.HistoricoAdapter;
import com.symbel.appejerciciopractico3.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by vale on 20/06/16.
 */
public class HistoricoFragment extends Fragment {


    public static RecyclerView rv;
    public final static String HISTORICO_PRODUCTOS = "historico";//nombre del fichero de preferences perfil.xml será

    public HistoricoFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Context context = getActivity();

        //TODO: Chapuza del siglo o mas




        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_historico, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);




      //  Log.d("Productos vistos", mListado_Productos.toString());


        /*HistoricoAdapter adapter = new HistoricoAdapter(getActivity(), mListado_Productos);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);*/


        return rootView;
    }

    public static void cargarHistorico(Context context, ArrayList<Producto> listado_productos){

        listado_productos = eliminarProductosNoVistos(context, listado_productos);


            HistoricoAdapter adapter = new HistoricoAdapter(context, listado_productos);
            HistoricoFragment.rv.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(context);
            HistoricoFragment.rv.setLayoutManager(llm);
            HistoricoFragment.rv.setHasFixedSize(true);

    }

    public static ArrayList<Producto> eliminarProductosNoVistos(Context context, ArrayList<Producto> listado_productos){
        //Creo un array con los productos vistos de las sharedPreferences
        ArrayList<Producto> historico_productos = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(HISTORICO_PRODUCTOS,Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        Map<String,String> productos_sharedPreferences = new HashMap<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            productos_sharedPreferences.put(entry.getKey().toString(),entry.getValue().toString());
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());

        }

        //Recorro el array de productos y guardo en un array los que esten en sharedPreferences
        if (productos_sharedPreferences.size() != 0) {
            int numeroProductos = listado_productos.size();
            for (int i = 0; i < numeroProductos; i++) {
                String idActual = listado_productos.get(i).getId();
                if (productos_sharedPreferences.containsKey(idActual)) {

                    //seteo la fecha de vista al producto
                    listado_productos.get(i).setFechaVisto(productos_sharedPreferences.get(idActual));
                    historico_productos.add(listado_productos.get(i));
                }
            }
            Log.d("Historico Productos", historico_productos.toString());
        }

        return historico_productos;
    }


}
