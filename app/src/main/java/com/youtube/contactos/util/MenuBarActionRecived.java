package com.youtube.contactos.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by henryyerrybravosanchez on 3/24/15.
 */
public class MenuBarActionRecived  extends BroadcastReceiver{

    public static final String FILTER_NAME="menu_bar";
    public static final int ELIMINAR_CONTACTO=1;
    public static final int SINCRONIZAR=3;
    private MenuActionListener listener;

    public MenuBarActionRecived(MenuActionListener listener){
        this.listener=listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion=intent.getIntExtra("operacion", -1);
        switch (operacion){
            case ELIMINAR_CONTACTO: listener.eliminarContactos(); break;
            case SINCRONIZAR: listener.sincronizarContactos(); break;
        }
    }

   public static interface MenuActionListener{
       public void eliminarContactos();
       public void sincronizarContactos();
   }

}
