package com.symbel.appejerciciopractico2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by estefi on 30/06/2016.
 */
public class Adapter extends ArrayAdapter<String>{

    private ArrayList<String> listaTitulos;
    private boolean[] isSelected;


        Adapter(Context context, ArrayList<String> list) {
            super(context, R.layout.list_item, list);
            listaTitulos = list;
            isSelected = new boolean[list.size()];
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

            parent.setTag(isSelected);


            holder.fila.setText(listaTitulos.get(position));

            return (item);
        }

    class ViewHolder {
        TextView fila;
    }
}






