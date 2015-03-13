package com.youtube.contactos;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.youtube.contactos.UtilDiseñoPager.ZoomOutPageTransforme;
import com.youtube.contactos.util.TabsPagerAdapter;

public class MainActivity extends Activity implements ActionBar.TabListener,ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private ActionBar actionBar;
    private String[] titulos= {
            "Crear Contactos",
            "Lista Contactos"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicirCabeceras();

    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void inicirCabeceras() {
        viewPager=(ViewPager)findViewById(R.id.pager);
        actionBar= getActionBar();
        adapter= new TabsPagerAdapter(getFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransforme());
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //agregar las fichas los titulos
        for (String s:titulos){
            ActionBar.Tab tab= actionBar.newTab().setText(s);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }
        viewPager.setOnPageChangeListener(this);
    }

    //<editor-fold desc="METODOS DE view change listener">
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //</editor-fold>

    //<editor-fold desc="METODOS DE TAB change LISTENER">
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    //</editor-fold>
}
