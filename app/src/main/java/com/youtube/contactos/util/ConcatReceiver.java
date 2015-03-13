package com.youtube.contactos.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class ConcatReceiver extends BroadcastReceiver{
    public static final int CONTACTO_AGREGADO=1;
    public static final int CONTACTO_ELIMINADO=3;
    public static final int CONTACTO_ACTUALIZAR=2;
    public static final int CONTACTO_COMPARTIR=4;

    private final ArrayAdapter<Contactos> adapter;

    public ConcatReceiver(ArrayAdapter<Contactos> adapter){
        this.adapter=adapter;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion=intent.getIntExtra("operacion", -1);
        switch (operacion){
            case CONTACTO_AGREGADO: agregarContacto(intent); break;
            case CONTACTO_ACTUALIZAR: actualizarContacto(intent); break;
            case CONTACTO_ELIMINADO: eliminarContacto(intent); break;
            case CONTACTO_COMPARTIR: compartirContacto(intent); break;
        }
    }

    private void actualizarContacto(Intent intent) {
        Contactos contacto=(Contactos)intent.getSerializableExtra("datos");
        int posicion=adapter.getPosition(contacto);
        adapter.insert(contacto, posicion);
    }

    private void eliminarContacto(Intent intent) {
        ArrayList<Contactos> lista=(ArrayList<Contactos>)intent.getSerializableExtra("datos");
        for(Contactos c:lista){
            adapter.remove(c);
        }
    }

    private void agregarContacto(Intent intent) {
        Contactos contacto=(Contactos)intent.getSerializableExtra("datos");
        adapter.add(contacto);
    }

    private void compartirContacto(Intent intent){
        
    }
}
