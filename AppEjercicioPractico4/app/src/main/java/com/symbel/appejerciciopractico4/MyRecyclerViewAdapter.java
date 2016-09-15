package com.symbel.appejerciciopractico4;

/**
 * Created by estefi on 15/09/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.symbel.appejerciciopractico4.model.Recados;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<Recados> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView fechaHora;
        TextView nombreCliente;
        TextView telefono;
        TextView direccionRecogida;
        TextView direccionEntrega;
        TextView descripcion;
        TextView fechaHoraMaxima;


        public DataObjectHolder(View itemView) {
            super(itemView);
            fechaHora = (TextView) itemView.findViewById(R.id.textViewFechaHora);
            nombreCliente = (TextView) itemView.findViewById(R.id.textViewNombreCliente);
            telefono = (TextView) itemView.findViewById(R.id.textViewTelefono);
            direccionRecogida = (TextView) itemView.findViewById(R.id.textViewDireccionRecogida);
            direccionEntrega = (TextView) itemView.findViewById(R.id.textViewDireccionEntrega);
            descripcion = (TextView) itemView.findViewById(R.id.textViewDescripcion);
            fechaHoraMaxima = (TextView) itemView.findViewById(R.id.textViewFechaHoraMaxima);

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(List<Recados> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.fechaHora.setText("Fecha/Hora: " + mDataset.get(position).getFechaHora());
        holder.nombreCliente.setText("Nombre: " + mDataset.get(position).getNombreCliente());
        holder.telefono.setText("Telefono: " + mDataset.get(position).getTelefono());
        holder.direccionRecogida.setText("Direccion Recogida: " + mDataset.get(position).getDireccionRecogida());
        holder.direccionEntrega.setText("Direccion Entrega: " + mDataset.get(position).getDireccionEntrega());
        holder.descripcion.setText("Descripcion: " + mDataset.get(position).getDescripcion());
        holder.fechaHoraMaxima.setText("Fecha/Hora Maxima: " + mDataset.get(position).getFechaHoraMaxima());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
