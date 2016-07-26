package com.symbel.appejerciciopractico3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.symbel.appejerciciopractico3.ItemClickListener;
import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.activity.DetalleActivity;
import com.symbel.appejerciciopractico3.model.Producto;

import java.util.ArrayList;

/**
 * Created by vale on 20/06/16.
 */
public class ListadoAdapter extends RecyclerView.Adapter<ListadoHolder> {
    private ArrayList<Producto> mListado_Productos;
    private Context context;




    // Provide a suitable constructor (depends on the kind of dataset)
    public ListadoAdapter(Context context, ArrayList<Producto> listado_productos) {
        this.context = context;
        mListado_Productos = listado_productos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListadoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //INFLATE A VIEW FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_card_item,null);

        //HOLDER
        ListadoHolder holder=new ListadoHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListadoHolder holder, int position) {
        holder.nombreTxt.setText(mListado_Productos.get(position).getNombre());
        holder.unidadesTxt.setText(mListado_Productos.get(position).getUnidades());

        //WHEN ITEM IS CLICKED
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //INTENT OBJ
                Intent i=new Intent(context,DetalleActivity.class);

                //ADD DATA TO OUR INTENT
                i.putExtra("Nombre",mListado_Productos.get(pos).getNombre());
                i.putExtra("Precio",mListado_Productos.get(pos).getPrecio());
                i.putExtra("Unidades",mListado_Productos.get(pos).getUnidades());
                i.putExtra("Descripcion",mListado_Productos.get(pos).getDescripcion());

                //START DETAIL ACTIVITY
                context.startActivity(i);

            }
        });
 }

    @Override
    public int getItemCount() {
        return mListado_Productos.size();
    }
}