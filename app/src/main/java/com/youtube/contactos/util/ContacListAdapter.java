package com.youtube.contactos.util;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youtube.contactos.R;

import java.util.List;

/**
 * Created by henryyerrybravosanchez on 3/12/15.
 */
public class ContacListAdapter extends ArrayAdapter<Contactos> {
    private Activity ctx;

    public ContacListAdapter(Activity context, List<Contactos> objects) {
        super(context, R.layout.row_lista_contacto, objects);
        ctx=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView=ctx.getLayoutInflater().inflate(R.layout.row_lista_contacto, parent, false);

        Contactos actual=this.getItem(position);
        iniciarCampos(convertView, actual);
        return convertView;
    }

    private void iniciarCampos(View view, Contactos actual) {
        TextView textView=(TextView)view.findViewById(R.id.txtListNombre);
        textView.setText(actual.getNombre());
        textView=(TextView)view.findViewById(R.id.txtListTelefono);
        textView.setText(actual.getTelefono());
        textView=(TextView)view.findViewById(R.id.txtListCorreo);
        textView.setText(actual.getCorreo());
        textView=(TextView)view.findViewById(R.id.txtListDireccion);
        textView.setText(actual.getDireccion());
        ImageView imageList=(ImageView)view.findViewById(R.id.imageView2);
        imageList.setImageURI(Uri.parse(actual.getImageUri()));
    }
}
