package com.symbel.appejerciciopractico3.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.symbel.appejerciciopractico3.R;
import com.symbel.appejerciciopractico3.adapter.PageAdapter;

public class MainActivity extends FragmentActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



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
    private static String[] titulo_tab = {"PRODUCTOS", "HISTÓRICO"};

    public static String getTitulo (int position){
        return titulo_tab[position];
    }

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Obtengo la referencia al ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        //Y le asigno su adpter
        pagerAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //Referencia al tablelayout
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tablay);
        //Creo dinamicamente los elementos
        tableLayout.addTab(tableLayout.newTab());
        tableLayout.addTab(tableLayout.newTab());

        //Asociar al viewpager para que cambie al cambiar el susodicho
        tableLayout.setupWithViewPager(viewPager);




}
}
