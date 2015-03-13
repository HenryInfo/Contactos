package com.youtube.contactos;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.youtube.contactos.util.ConcatReceiver;
import com.youtube.contactos.util.ContacListAdapter;
import com.youtube.contactos.util.Contactos;

import java.util.ArrayList;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class ListaContactosFragment extends Fragment{
    private ArrayAdapter<Contactos> adapter;
    private ListView lista;
    private ConcatReceiver receiver;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.lista_fragmento_contactos, container, false);
        inicializarComponentes(rootView);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver );
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver= new ConcatReceiver(adapter);
        getActivity().registerReceiver(receiver, new IntentFilter("lc"));
    }

    private void inicializarComponentes(View v){
        lista=(ListView)v.findViewById(R.id.listView);
        adapter = new ContacListAdapter(getActivity(), new ArrayList<Contactos>());
        adapter.setNotifyOnChange(true);
        lista.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete: eliminarItem(item) ; return true;
            case R.id.menu_compartir: compartirItem(item); return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    private void compartirItem(MenuItem item) {
        SparseBooleanArray array= lista.getCheckedItemPositions();
        ArrayList<Contactos>seleccion= new ArrayList<Contactos>();
        for (int i=0; i<array.size(); i++){
            int posicion= array.keyAt(i);
            if(array.valueAt(i)){
                seleccion.add(adapter.getItem(posicion));
                Intent intent= new Intent("lc");
                intent.putExtra("operacion", ConcatReceiver.CONTACTO_COMPARTIR);
                intent.putExtra("datos", seleccion);
                getActivity().sendBroadcast(intent);
                lista.clearChoices();
            }
        }

    }

    private void eliminarItem(MenuItem item) {
        SparseBooleanArray array= lista.getCheckedItemPositions();
        ArrayList<Contactos>seleccion= new ArrayList<Contactos>();
        for (int i=0; i<array.size(); i++){
             int posicion= array.keyAt(i);
            if(array.valueAt(i))
                seleccion.add(adapter.getItem(posicion));
            Intent intent= new Intent("lc");
            intent.putExtra("operacion", ConcatReceiver.CONTACTO_ELIMINADO);
            intent.putExtra("datos", seleccion);
            getActivity().sendBroadcast(intent);
            lista.clearChoices();
        }
    }
}
