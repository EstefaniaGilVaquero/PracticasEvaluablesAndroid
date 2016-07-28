package com.symbel.appejerciciopractico3.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.activity.DetalleActivity;
import com.symbel.appejerciciopractico3.model.Producto;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by vale on 20/06/16.
 */
public class ListadoAdapter extends RecyclerView.Adapter<ListadoHolder> {
    private ArrayList<Producto> mListado_Productos;
    private Context context;
    public final static String HISTORICO_PRODUCTOS = "historico";//nombre del fichero de preferences perfil.xml será




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
        holder.precioTxt.setText(mListado_Productos.get(position).getPrecio() + "€");

        //Listener del listado de productos
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //Guardo la fecha y hora del dispositivo
                String fechaHoraActual = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());

                //Cuando se pulsa un item se guarda en preferencias de usuario
                SharedPreferences prefs = context.getSharedPreferences(HISTORICO_PRODUCTOS,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(mListado_Productos.get(pos).getId(), fechaHoraActual);
                editor.commit();

                //Pinto preferencias guardadas de prueba
                Map<String, ?> allEntries = prefs.getAll();
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                }

                //Intent de llamada a la actividad detalle
                Intent i=new Intent(context,DetalleActivity.class);

                //Metemos datos en el intent
                i.putExtra("Nombre",mListado_Productos.get(pos).getNombre());
                i.putExtra("Precio",mListado_Productos.get(pos).getPrecio());
                i.putExtra("Unidades",mListado_Productos.get(pos).getUnidades());
                i.putExtra("Descripcion",mListado_Productos.get(pos).getDescripcion());

                //Lanzar actividad
                context.startActivity(i);

            }
        });
 }

    @Override
    public int getItemCount() {
        return mListado_Productos.size();
    }
}