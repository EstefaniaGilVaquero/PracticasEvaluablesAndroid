package com.symbel.appejerciciopractico3.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import com.symbel.appejerciciopractico3.activity.MainActivity;
import com.symbel.appejerciciopractico3.fragment.HistoricoFragment;

public class PageAdapterPropio extends FragmentStatePagerAdapter {

    //En el construcutor, recojo el FragmentManager, que intermanete,
    //se encargará de suministrar los fragmentos o trozos pantallas
    public PageAdapterPropio(FragmentManager fm) {
        super(fm);
    }

    //Este método es análogo a nuestro getView del Adapter que usábamos con ListView
    //Con la salvedad de que aquí, en vez de vistas, devolveremos fragments
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position)
        {
            case 0: fragment = new ListFragment();
                break;
            case 1: fragment = new HistoricoFragment();
                break;
        }

        return  fragment;
    }

    //Este método se usa sólo para saber cuántas pantallas tengo y si el adpter ha llegado al final
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return MainActivity.getTitulo(position);
    }


}
