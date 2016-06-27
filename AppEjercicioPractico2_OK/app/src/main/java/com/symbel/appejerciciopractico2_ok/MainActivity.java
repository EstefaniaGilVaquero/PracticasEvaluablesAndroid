package com.symbel.appejerciciopractico2_ok;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    boolean[] isSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewHolder holder = new ViewHolder();
        holder.text = (TextView) .findViewById(R.id.text_view);
        this.setTag




        list_view = (ListView)findViewById(R.id.LstColores);
        for (int i=0; i<=30; i++){
            list.add(String.valueOf("Fila: " + i));

        }

        isSelected = new boolean[list.size()];

        adapter = new ArrayAdapter<String>(this,R.layout.list_item, list);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String item = list.get(position);
                if(isSelected[position]){
                    view.setBackgroundColor(Color.WHITE);
                    isSelected[position] = false;
                }else if(!isSelected[position]){
                    view.setBackgroundColor(Color.GREEN);
                    isSelected[position] = true;
                }
            }
        });
    }

    static class ViewHolder {
        TextView textViewItem;
    }





}
