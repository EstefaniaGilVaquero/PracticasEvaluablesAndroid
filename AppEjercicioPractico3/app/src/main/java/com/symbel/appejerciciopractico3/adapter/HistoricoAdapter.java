package com.symbel.appejerciciopractico3.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.model.Producto;

import java.util.ArrayList;

/**
 * Created by vale on 20/06/16.
 */
public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.MyViewHolder> {
    private ArrayList<Producto> mListado_Productos;
    private Context context;



    // Provide a suitable constructor (depends on the kind of dataset)
    public HistoricoAdapter(Context context, ArrayList<Producto> listado_productos) {
        this.context = context;
        mListado_Productos = listado_productos;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
       // public CardView mCardView;
        public TextView mNombreTxt;
        public TextView mFechaTxt;
        public MyViewHolder(View v) {
            super(v);

            //mCardView = (CardView) v.findViewById(R.id.card_view);
            mNombreTxt = (TextView) v.findViewById(R.id.tv_nombre);
            mFechaTxt = (TextView) v.findViewById(R.id.tv_fecha);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HistoricoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historico_card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mNombreTxt.setText(mListado_Productos.get(position).getNombre());
        holder.mFechaTxt.setText(mListado_Productos.get(position).getFechaVisto());
    }

    @Override
    public int getItemCount() {
        return mListado_Productos.size();
    }
}