package com.youtube.contactos.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.contactos.util.AsyncTask;
import com.youtube.contactos.util.JSONBean;

import java.util.List;

/**
 * Created by henryyerrybravosanchez on 3/30/15.
 */
public class HttpDispatcher {

    private final Context context;
    private ObjectMapper mapper;
    private final String BASE_URL_ADDRESS="http://%s:&s/jsonweb/rest/contacto";
    private final String SERVER_ADRESS;
    private final String SERVER_PORT;
    private final String REGISTRY_OWNER;

    public HttpDispatcher(Context context) {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SERVER_ADRESS=pref.getString("server_address", null);
        SERVER_PORT=pref.getString("server_port", null);
        REGISTRY_OWNER=pref.getString("username", null);
        mapper=new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.context = context;
    }
    public <T> void doget(Class<T> resulType, AsyncTask<T>listener ){
        StringBuilder builder= new StringBuilder(String.format(BASE_URL_ADDRESS, SERVER_ADRESS, SERVER_PORT));
        String url= builder.append("/propietario/").append(REGISTRY_OWNER).toString();
        if (wifiEnabled()){
            //TODO: implementar metodo wifi

        }else{
            Toast.makeText(context,"Wifi no esta disponible", Toast.LENGTH_LONG).show();
        }
    }
    public void doPost(JSONBean bean, AsyncTask<List<String>> listener){
        StringBuilder builder= new StringBuilder(String.format(BASE_URL_ADDRESS, SERVER_ADRESS, SERVER_PORT));
        String url= builder.toString();
        if (wifiEnabled()){
            //TODO: implementar metodo wifi

        }else{
            Toast.makeText(context,"Wifi no esta disponible", Toast.LENGTH_LONG).show();
        }
    }
    public void doPut(JSONBean bean, AsyncTask<List<String>> listener){
        StringBuilder builder= new StringBuilder(String.format(BASE_URL_ADDRESS, SERVER_ADRESS, SERVER_PORT));
        String url= builder.append("/").append(bean.getServerId()).toString();

        if (wifiEnabled()){
            //TODO: implementar metodo wifi

        }else{
            Toast.makeText(context,"Wifi no esta disponible", Toast.LENGTH_LONG).show();
        }
    }
    public void doDelete(JSONBean bean, AsyncTask<List<String>> listener){
        StringBuilder builder= new StringBuilder(String.format(BASE_URL_ADDRESS, SERVER_ADRESS, SERVER_PORT));
        String url= builder.append("/").append(bean.getServerId()).toString();

        if (wifiEnabled()){
            //TODO: implementar metodo wifi

        }else{
            Toast.makeText(context,"Wifi no esta disponible", Toast.LENGTH_LONG).show();
        }
    }
    private boolean wifiEnabled() {

        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info= manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info !=null && info.isConnected();

    }
}
