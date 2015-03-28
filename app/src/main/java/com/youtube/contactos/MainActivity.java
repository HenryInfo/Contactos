package com.youtube.contactos;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.youtube.contactos.util.ConcatReceiver;
import com.youtube.contactos.util.DataBaseHelper;
import com.youtube.contactos.util.MenuBarActionRecived;

public class MainActivity extends OrmLiteBaseActivity<DataBaseHelper> implements View.OnTouchListener{

    private ActionBar actionBar;
    private ImageButton btnCrear, btnLista, btnSincro, btnEliminar;
    private AgregarContactoFragment agregarContacto;
    private ListaContactosFragment lista;

    private ConcatReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarActionBar();
        iniciarComponentes();
    }

    @Override
     public void onPause() {
     super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver= new ConcatReceiver(this);
        registerReceiver(receiver, new IntentFilter("lc"));
    }

    private void iniciarComponentes() {
        btnCrear= (ImageButton) findViewById(R.id.btnCrear);
        btnCrear.setOnTouchListener(this);

        btnLista= (ImageButton) findViewById(R.id.btnLista);
        btnLista.setOnTouchListener(this);

        btnEliminar = (ImageButton) findViewById(R.id.btnEliminar);
        btnEliminar.setOnTouchListener(this);

        btnSincro= (ImageButton) findViewById(R.id.btnSincronizar);
        btnSincro.setOnTouchListener(this);
        cargarFragmento(getFragmentoLista());
    }

    private void cargarFragmento(Fragment fragment) {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, fragment);
        fragmentTransaction.commit();
    }


    private void iniciarActionBar() {

        actionBar= getActionBar();

        actionBar.setHomeButtonEnabled(false);

    }

    public Fragment getFragmentoLista() {
        if (lista==null){
            lista=  new ListaContactosFragment();
        }
        return lista;
    }
    public Fragment getFragmentoCrear(){
        if(agregarContacto==null){
            agregarContacto= new AgregarContactoFragment();
        }
        return agregarContacto;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageButton  btn= (ImageButton) v;
        int actionMasked= event.getActionMasked();
        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                btn.setColorFilter(R.color.entintado_oscuro);
                btn.invalidate();
                cambiarFragment(btn);
                break;
            case MotionEvent.ACTION_UP:
                btn.clearColorFilter();
                btn.invalidate();
                break;
        }
        return true;
    }

    private void cambiarFragment(View v) {
        switch (v.getId()){
            case R.id.btnCrear:
                cargarFragmento(getFragmentoCrear());
                break;
            case R.id.btnLista: cargarFragmento(getFragmentoLista());
                break;
            case R.id.btnEliminar: notificarEliminar(); break; //TODO eliminar
            case R.id.btnSincronizar: notificarSincronizar(); break;//TODO sincronizar
        }
    }

    private void notificarEliminar() {
        Intent intent= new Intent(MenuBarActionRecived.FILTER_NAME);
        intent.putExtra("operacion", MenuBarActionRecived.ELIMINAR_CONTACTO);
        sendBroadcast(intent );

    }

    private void notificarSincronizar() {
        Intent intent= new Intent(MenuBarActionRecived.FILTER_NAME);
        intent.putExtra("operacion", MenuBarActionRecived.SINCRONIZAR );
        sendBroadcast(intent );
    }
}
