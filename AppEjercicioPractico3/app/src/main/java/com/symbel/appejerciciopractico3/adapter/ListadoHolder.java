package com.symbel.appejerciciopractico3.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.symbel.appejerciciopractico3.ItemClickListener;
import com.symbel.appejerciciopractico3.R;

/**
 * Created by Hp on 3/15/2016.
 */
public class ListadoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //OUR VIEWS

    TextView nombreTxt;
    TextView unidadesTxt;
    private ItemClickListener itemClickListener;

//our contructor
    public ListadoHolder(View itemView) {
        super(itemView);

        nombreTxt= (TextView) itemView.findViewById(R.id.tv_nombre);
        unidadesTxt= (TextView) itemView.findViewById(R.id.tv_unidades);

        itemView.setOnClickListener(this);
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
