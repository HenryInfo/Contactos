package com.youtube.contactos.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class ConcatReceiver extends BroadcastReceiver{
    public static final int CONTACTO_AGREGADO=1;
    public static final int CONTACTO_ELIMINADO=3;
    public static final int CONTACTO_ACTUALIZAR=2;
    public static final int CONTACTO_COMPARTIR=4;
    private final  OrmLiteBaseActivity<DataBaseHelper> activity ;

    public ConcatReceiver(OrmLiteBaseActivity<DataBaseHelper> activity ){
        this.activity =activity;
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
        if (activity!=null){
            //Actualizamos a la bd
            DataBaseHelper helper= activity.getHelper();
            RuntimeExceptionDao<Contactos, Integer> dao=helper.getRuntimeExceptionDao();
            dao.update(contacto);
        }
    }

    private void eliminarContacto(Intent intent) {
        ArrayList<Contactos> lista=(ArrayList<Contactos>)intent.getSerializableExtra("datos");
        if (activity!=null){
            //Eliminamos a la bd
            DataBaseHelper helper= activity.getHelper();
            RuntimeExceptionDao<Contactos, Integer> dao=helper.getRuntimeExceptionDao();
            dao.delete(lista);
        }
    }

    private void agregarContacto(Intent intent) {
        Contactos contacto=(Contactos)intent.getSerializableExtra("datos");
        if (activity!=null){
            //Agregaamos a la bd
            DataBaseHelper helper= activity.getHelper();
            RuntimeExceptionDao<Contactos, Integer> dao=helper.getRuntimeExceptionDao();
            dao.create(contacto);
        }
    }

    private void compartirContacto(Intent intent){
        ArrayList<Contactos> lista=(ArrayList<Contactos>)intent.getSerializableExtra("datos");
       intent = new Intent(Intent.ACTION_SEND);
       intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, lista);
    }

}
