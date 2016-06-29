package com.symbel.appejerciciopractico2_ok;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    boolean[] isSelected;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        list_view = (ListView) findViewById(R.id.LstColores);
        for (int i = 0; i <= 30; i++) {
            list.add(String.valueOf("Fila: " + i));

        }

        isSelected = new boolean[list.size()];

        //Adaptador
        AdaptadorColores adaptador = new AdaptadorColores(this, list);


        list_view.setAdapter(adaptador);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String item = list.get(position);
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



    class AdaptadorColores extends ArrayAdapter<String> {

        AdaptadorColores(Context context, ArrayList<String> list) {
            super(context, R.layout.list_item, list);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            ViewHolder holder;

            if (item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.list_item, null);

                holder = new ViewHolder();
                holder.fila = (TextView) item.findViewById(R.id.text_view);

                item.setTag(holder);


            } else {
                holder = (ViewHolder) item.getTag();

            }

            if (isSelected[position]) {
                item.setBackgroundColor(Color.GREEN);
            } else {
                item.setBackgroundColor(Color.WHITE);
            }

            holder.fila.setText(list.get(position));

            return (item);
        }
    }

    static class ViewHolder {
        TextView fila;
    }


//    class AdaptadorTitulares extends ArrayAdapter<Titulo> {
//
//        AdaptadorTitulares(Context context, Titulo[] datos) {
//            super(context, R.layout.listitem_titular, datos);
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            View item = convertView;
//            ViewHolder holder;
//
//            if (item == null) {
//                LayoutInflater inflater = LayoutInflater.from(getContext());
//                item = inflater.inflate(R.layout.listitem_titular, null);
//
//                holder = new ViewHolder();
//                holder.titulo = (TextView) item.findViewById(R.id.LblTitulo);
//                holder.subtitulo = (TextView) item.findViewById(R.id.LblSubTitulo);
//
//                item.setTag(holder);
//            } else {
//                holder = (ViewHolder) item.getTag();
//            }
//
//            holder.titulo.setText(datos[position].getTitulo());
//            holder.subtitulo.setText(datos[position].getSubtitulo());
//
//            return (item);
//        }
//    }


}
