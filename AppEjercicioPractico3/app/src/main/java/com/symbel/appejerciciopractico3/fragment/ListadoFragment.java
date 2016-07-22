package com.symbel.appejerciciopractico3.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.symbel.appejerciciopractico3.R;

/**
 * Created by estefi on 21/07/2016.
 */
public class ListadoFragment extends Fragment {

    //Llamo al constructor del padre. Necesario para iniciar el fragment
    public ListadoFragment() {
        super();
    }

    //Sobreescribo este método para devolver la vista raíz del fragment
    //inflando para ello el layout que representa la vista de dicho fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_listado, container, false);

        return rootView;
    }
}
