package com.symbel.appejerciciopractico3.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vale on 20/06/16.
 */
public class HistoricoAdapter extends Fragment {



    //Llamo al constructor del padre. Necesario para iniciar el fragment
    public HistoricoAdapter() {
        super();
    }

    //Sobreescribo este método para devolver la vista raíz del fragment
    //inflando para ello el layout que representa la vista de dicho fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_deslizante1, container, false);

        return rootView;
    }
}

