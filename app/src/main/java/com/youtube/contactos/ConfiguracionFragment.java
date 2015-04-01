package com.youtube.contactos;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by henryyerrybravosanchez on 3/30/15.
 */
public class ConfiguracionFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuracion);
    }
}
