package com.symbel.appejerciciopractico3.activity;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.symbel.appejerciciopractico3.ObtenerProductos;
import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.adapter.PageAdapterPropio;
import com.symbel.appejerciciopractico3.utils.UtilInternet;

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

        if(UtilInternet.isNetworkAvailable(this)){
            Log.d(getClass().getCanonicalName(), "SI HAY INTERNET");
            new ObtenerProductos().execute("Paco");
        }else{
            Log.d(getClass().getCanonicalName(), "NO HAY INTERNET");
            FragmentManager fm = this.getFragmentManager();
            DialogFragment dialogo = new DialogFragment();
            dialogo.show(fm, "aviso");
        }









    }


    //Sobreescribiendo este método, consigo personalizar el comportamiento del botón "hacia atrás"
    //en esta actividad.
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // Si estoy en la pantalla inicial, hago que se cierre la app (comportamiento por defecto logrado invocando al padre)
            super.onBackPressed();
        } else {
            // Si no, paso a una pantalla anterior
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}
