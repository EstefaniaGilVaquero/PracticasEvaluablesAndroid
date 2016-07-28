package com.symbel.appejerciciopractico3.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.adapter.PageAdapterPropio;

public class MainActivity extends FragmentActivity {



    /**
     * El VIEWPAGER permite transitar entre las pantallas. Se encarga de la animación y atiende al desplazmiento (swipe)
     * Es el elemento contenedor
     */
    private ViewPager viewPager;

    /**
     * El PAGER ADAPTER es el "adapter" del VIEWPAGER. Es el proveedor de "patanllas". CUando el viewpager necesite transitar
     * será el pageradapter el que le de las vistas (fragments en este caso)
     */
    private PagerAdapter pagerAdapter;

    //Titulo visible para cada fragment
    private static String[] titulo_tab = {"LISTADO", "HISTORICO"};

    public static String getTitulo (int position){
        return titulo_tab[position];
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);



        //TODO: y si llamo aqui a obtener productos e imagenes?
       // new DescargarImagenesTask().execute(Constantes.URL_SERVICIO_IMAGENES);
/*
        if (UtilInternet.isNetworkAvailable(context)) {
            Log.d(getClass().getCanonicalName(), "SI HAY INTERNET");
            new ObtenerProductos(context).execute();
        } else {
            Log.d(getClass().getCanonicalName(), "NO HAY INTERNET");

        }*/

        // Obtengo la referencia al ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //Y le asigno su adpter
        pagerAdapter = new PageAdapterPropio(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //Referencia al tablelayout
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tab_layout);
        //Creo dinamicamente los elementos
        for (int i = 0; i< titulo_tab.length; i++){
            tableLayout.addTab(tableLayout.newTab());
        }


        //Asociar al viewpager para que cambie al cambiar el susodicho
        tableLayout.setupWithViewPager(viewPager);

     }
}
