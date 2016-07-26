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
public class ListadoAdapter extends RecyclerView.Adapter<ListadoAdapter.MyViewHolder> {
    private ArrayList<Producto> mListado_Productos;
    private Context context;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ItemClickListener itemClickListener;
        public CardView mCardView;
        public TextView mTextView;
        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListadoAdapter(Context context, ArrayList<Producto> listado_productos) {
        this.context = context;
        mListado_Productos = listado_productos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListadoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listado_card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);

    return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mListado_Productos.get(position).getNombre());

        //WHEN ITEM IS CLICKED
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //INTENT OBJ
                Intent i=new Intent(context,DetalleActivity.class);

                //ADD DATA TO OUR INTENT
                i.putExtra("Nombre",mListado_Productos.get(pos).getNombre());
                i.putExtra("Precio",mListado_Productos.get(pos).getNombre());
                i.putExtra("Unidades",mListado_Productos.get(pos).getNombre());
                i.putExtra("Descripcion",mListado_Productos.get(pos).getNombre());






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