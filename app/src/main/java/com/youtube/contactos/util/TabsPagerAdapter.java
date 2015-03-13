package com.youtube.contactos.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.youtube.contactos.AgregarContactoFragment;
import com.youtube.contactos.ListaContactosFragment;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter{


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new AgregarContactoFragment();
            case 1: return new ListaContactosFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
