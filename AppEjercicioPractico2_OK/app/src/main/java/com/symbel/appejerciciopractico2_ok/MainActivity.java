package com.symbel.appejerciciopractico2_ok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        //Indicamos el layoutManager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        List<ItemObject> filas = returnListItems();

        //Indicamos el adapter

        adapter = new RecyclerViewAdapter(MainActivity.this, filas);
        recyclerView.setAdapter(adapter);
    }

    private List<ItemObject> returnListItems(){
        List<ItemObject> items = new ArrayList<ItemObject>();

        for (int i=0; i<=30; i++){
            items.add(new ItemObject("Fila" + i));

        }
        return items;
    }
}