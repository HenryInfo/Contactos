package com.youtube.contactos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by henryyerrybravosanchez on 3/30/15.
 */
public class ConfiguracionActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction transactionManager= fragmentManager.beginTransaction();
        ConfiguracionFragment configuracionFragment= new ConfiguracionFragment();
        transactionManager.replace(android.R.id.content, configuracionFragment);
        transactionManager.commit();
    }
}
