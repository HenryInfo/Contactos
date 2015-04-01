package com.youtube.contactos;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.youtube.contactos.util.ConcatReceiver;
import com.youtube.contactos.util.DataBaseHelper;
import com.youtube.contactos.util.MenuBarActionRecived;

public class MainActivity extends OrmLiteBaseActivity<DataBaseHelper> implements View.OnTouchListener{

    private ActionBar actionBar;
    private ImageButton btnCrear, btnLista, btnSincro, btnEliminar;
    private AgregarContactoFragment agregarContacto;
    private ListaContactosFragment lista;
    private final int CONFIG_REQUEST_CODE =0;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.setClass(this, ConfiguracionActivity.class);
        startActivityForResult(intent, CONFIG_REQUEST_CODE);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CONFIG_REQUEST_CODE){
            SharedPreferences shp= PreferenceManager.getDefaultSharedPreferences(this);
            String username= shp.getString("username", null);
            String msg= String.format("Datos del usuario '%s' ha sido guardado", username);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
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
