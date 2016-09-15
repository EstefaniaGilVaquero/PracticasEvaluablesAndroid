package com.symbel.appejerciciopractico4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.symbel.appejerciciopractico4.model.Recados;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    public static String mURL = "http://elrecadero-ebtm.rhcloud.com/ObtenerListaRecados";
    //public static String mURL = "http://www.hrsanroque.com/galeria/slider/18.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(getClass().getCanonicalName(), "MainActivity iniciado");

        //textView = (TextView) findViewById(R.id.textView);

        //Se lanza un servicio
        Intent intentService = null;
        intentService = new Intent(this,MyService.class);
        startService(intentService);


        //Alimentamos el cardView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<Recados> getDataSet() {
        // results = new ArrayList<DataObject>();
        /*for (int index = 0; index < 20; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }*/

        ArrayList<Recados> l_recados = new ArrayList<Recados>();

        l_recados.add(new Recados("Sep 15, 2016 6:46:50 AM", "Anguita", "659887433", "Calle del Buenismo",
                    "Registro Propiedad 2", "Obtener nota simple del registro de la propiedad", "Sep 15, 2016 7:16:50 AM"));

        l_recados.add(new Recados("Sep 15, 2016 6:46:50 AM", "asdfasdf", "659887433", "Calle del Buenismo",
                "asdfasdf Propiedad 2", "Obtener nota simple del registro de la ffff", "Sep 15, 2016 7:16:50 AM"));

        l_recados.add(new Recados("Sep 15, 2016 6:46:50 AM", "Anguita", "659887433", "Calle del Buenismo",
                "Registro asdfasdf 2", "asdfasdf nota simple del registro de la ff", "Sep 15, 2016 7:16:50 AM"));

        l_recados.add(new Recados("Sep 15, 2016 6:46:50 AM", "asdfsdf", "659887433", "Calle del Buenismo",
                "Registro Propiedad 2", "Obtener asdfasdfsf simple del registro de la propiedad", "Sep 15, 2016 7:16:50 AM"));


        return l_recados;
    }


}
