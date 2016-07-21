package com.symbel.appejerciciopractico2;

import android.content.Context;
import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    ArrayList<String> list = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.LstColores);
        for (int i = 0; i <= 30; i++) {
            list.add(String.valueOf("Fila: " + i));

        }

        //Comprobamos si se ha dado la vuelta al dispositivo y por lo tanto tenemos elementos seleccionados
//        if (savedInstanceState != null) {
//            isSelected = savedInstanceState.getBooleanArray(null);
//        }else {
//            isSelected = new boolean[list.size()];
//        }

        //Adaptador
        Adapter adaptador = new Adapter(this, list);


        list_view.setAdapter(adaptador);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                boolean[] isSelected = (boolean[]) parent.getTag();
                if (isSelected[position]) {
                    view.setBackgroundColor(Color.WHITE);
                    isSelected[position] = false;
                } else if (!isSelected[position]) {
                    view.setBackgroundColor(Color.GREEN);
                    isSelected[position] = true;
                }
            }
        });

    }




    //Sobreescribimos el metodo para guardar las filas seleccionadas
    //en caso de cambio de orientacion
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putBooleanArray(null, isSelected);
//
//    }
}