package com.symbel.appejerciciopractico2_ok;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    public TextView songTitle;


    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        songTitle = (TextView)itemView.findViewById(R.id.song_title);

    }

    @Override
    public void onClick(View view) {
        if (selectedItems.get(getAdapterPosition(), false)) {
            selectedItems.delete(getAdapterPosition());
            view.setSelected(false);
        }
        else {
            selectedItems.put(getAdapterPosition(), true);
            view.setSelected(true);
        }
    }
}