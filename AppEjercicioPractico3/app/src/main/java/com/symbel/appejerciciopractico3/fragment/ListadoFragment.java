package com.symbel.appejerciciopractico3.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.symbel.appejerciciopractico3.ObtenerProductos;
import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.utils.UtilInternet;

import java.util.concurrent.ExecutionException;

/**
 * Created by estefi on 21/07/2016.
 */
public class ListadoFragment extends Fragment {
    //DATA SOURCE

    public static RecyclerView rv;

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
                new ObtenerProductos(context).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(getClass().getCanonicalName(), "NO HAY INTERNET");

        }
        return rootView;
    }
}