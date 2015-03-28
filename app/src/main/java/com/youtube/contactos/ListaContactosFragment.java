package com.youtube.contactos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.youtube.contactos.util.ConcatReceiver;
import com.youtube.contactos.util.Contactos;
import com.youtube.contactos.util.DataBaseHelper;
import com.youtube.contactos.util.MenuBarActionRecived;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class ListaContactosFragment extends Fragment implements MenuBarActionRecived.MenuActionListener, ContactoFragment.FragmentCheckedListener
{

    MenuBarActionRecived receiver;
    private List<ContactoFragment> fragmentoSeleccionados= new ArrayList<ContactoFragment>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.lista_fragmento_contactos, container, false);
        inicializarComponentes(rootView);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver= new MenuBarActionRecived(this);
        getActivity().registerReceiver(receiver, new IntentFilter(MenuBarActionRecived.FILTER_NAME));
    }


    private void inicializarComponentes(View v){
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        OrmLiteBaseActivity<DataBaseHelper> activity=getOrmLiteBaseActivity();
        if(activity!=null){
            DataBaseHelper helper= activity.getHelper();
            RuntimeExceptionDao<Contactos, Integer> dao= helper.getRuntimeExceptionDao();
            List<Contactos> contactos=dao.queryForAll();
            for (Contactos con:contactos){
                ContactoFragment contactoFragment=ContactoFragment.crearInstancia(con, this);
                fragmentTransaction.add(R.id.lista_fragmento_contacto,contactoFragment);
            }
        }
        fragmentTransaction.commit();
    }


    private OrmLiteBaseActivity<DataBaseHelper> getOrmLiteBaseActivity(){
        Activity activity= getActivity();
        if(activity instanceof OrmLiteBaseActivity){
            return (OrmLiteBaseActivity<DataBaseHelper>) activity;
        }
        return null;
    };

    @Override
    public void eliminarContactos() {
        String mensaje="¿Esta seguro de eliminar los contactos seleccionados?";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_alerta).setTitle("Confirmar");
        builder.setMessage(mensaje);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ArrayList<Contactos> seleccion = new ArrayList<Contactos>();
                for (ContactoFragment contactoFragment : fragmentoSeleccionados) {
                    seleccion.add(contactoFragment.getContacto());
                    transaction.remove(contactoFragment);
                }
                fragmentoSeleccionados.clear();
                transaction.commit();
                Intent intent = new Intent("lc");
                intent.putExtra("operacion", ConcatReceiver.CONTACTO_ELIMINADO);
                intent.putExtra("datos", seleccion);
                getActivity().sendBroadcast(intent);
            }
        });
        builder.setNegativeButton("NO", null);
        builder.show();
    }

    @Override
    public void sincronizarContactos() {
        Toast.makeText(getActivity(), "Sincronizar DAtos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fragmentChecked(ContactoFragment cFrag, boolean isChecked) {
        if (isChecked) fragmentoSeleccionados.add(cFrag);
        else fragmentoSeleccionados.remove(cFrag);
    }
}
