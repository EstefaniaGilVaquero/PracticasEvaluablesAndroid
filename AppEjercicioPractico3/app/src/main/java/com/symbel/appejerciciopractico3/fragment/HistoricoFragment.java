package com.symbel.appejerciciopractico3.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.symbel.appejerciciopractico3.R;

/**
 * Created by vale on 20/06/16.
 */
public class HistoricoFragment extends Fragment {



    public HistoricoFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_historico, container, false);

        return rootView;
    }
}

