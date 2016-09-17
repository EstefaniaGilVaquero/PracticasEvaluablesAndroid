package com.symbel.appejerciciopractico4;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.symbel.appejerciciopractico4.model.Recados;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private static ImageButton FAB;

    public static String mURL = "http://elrecadero-ebtm.rhcloud.com/ObtenerListaRecados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(getClass().getCanonicalName(), "MainActivity iniciado");

        //Floation Action Buton para refrescar
        FAB = (ImageButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getCanonicalName(), "Refresco MainActivity");
                finish();
               overridePendingTransition(0, 0);//Para que la transicion no se note tanto al refrescar
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });



        //Llamada a descargaTareas pasando url como parametro
        Log.d(getClass().getCanonicalName(), "Llamada a AsynkTask DescargaTareas");
        ArrayList<Recados> l_recados = null;
        DescargaTareas descargaTareas = new DescargaTareas();
        try {
            l_recados = descargaTareas.execute(MainActivity.mURL).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.d(getClass().getCanonicalName(), "");

        //Alimentamos el cardView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(ordenarArrayAmazing(l_recados));
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

    public static ArrayList<Recados> ordenarArrayAmazing(ArrayList<Recados> l_recados){
        //Esta es el mejor metodo para ordenar arrays que he visto en mi vida. Ole ole ole!!!
        //Ordenamos por FechaHora
        Collections.sort(l_recados, new Comparator<Recados>(){
            public int compare(Recados s1, Recados s2) {
                return (s1.getFechaHora().compareTo(s2.getFechaHora()));

            }
        });

        return l_recados;
    }


}
