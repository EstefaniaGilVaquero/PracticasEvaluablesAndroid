package com.symbel.appejerciciopractico3.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.symbel.appejerciciopractico3.ItemClickListener;
import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.activity.DetalleActivity;

/**
 * Created by vale on 20/06/16.
 */
public class ListadoAdapter extends RecyclerView.Adapter<ListadoAdapter.MyViewHolder> {
    private String[] mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
    public ListadoAdapter(String[] myDataset) {
        mDataset = myDataset;
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
        holder.mTextView.setText(mDataset[position]);

//        //BIND DATA
//        holder.nameTxt.setText(players[position]);
//        holder.posTxt.setText(positions[position]);
//        holder.img.setImageResource(images[position]);

        //WHEN ITEM IS CLICKED
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //TODO: Hay obtener los datos de la bd, que aun no se como

                //Declaramos intent
                Intent i=new Intent(v.getContext(),DetalleActivity.class);

                //Metemos datos en el intent
                i.putExtra("Nombre","Gafas de ventista");
                i.putExtra("Precio","50");
                i.putExtra("Unidades","9");
                i.putExtra("Descripcion","9");
                //i.putExtra("Imagen","??????");

                //Iniciamos la actividad de detalle
                v.getContext().startActivity(i);

            }
        });

}

    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}