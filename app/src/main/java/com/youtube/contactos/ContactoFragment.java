package com.youtube.contactos;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.youtube.contactos.util.Contactos;

/**
 * Created by henryyerrybravosanchez on 3/28/15.
 */
public class ContactoFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private FragmentCheckedListener listener;
    private TextView viewNombre, viewDireccion, viewTelefono, viewEmail;
    private ImageView imgContacto;
    private CheckBox checkBox;
    private Contactos contactoActual;

    public static ContactoFragment crearInstancia(Contactos contacto, FragmentCheckedListener fcheck ){
        ContactoFragment contactoFragment= new ContactoFragment();
        contactoFragment.contactoActual=contacto;
        contactoFragment.listener=fcheck;
        return  contactoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.row_lista_contacto, container, false);
        setHasOptionsMenu(true);
        inicializarComponentes(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(contactoActual!=null){
            viewNombre.setText(contactoActual.getNombre());
            viewDireccion.setText(contactoActual.getDireccion());
            viewEmail.setText(contactoActual.getCorreo());
            viewTelefono.setText(contactoActual.getTelefono());

            if (contactoActual.getImageUri()!=null){
                imgContacto.setImageURI(Uri.parse(contactoActual.getImageUri()));

            }else {

                imgContacto.setImageResource(R.drawable.user_icon);
            }
        }
    }

    private void inicializarComponentes(View view) {
        viewNombre= (TextView) view.findViewById(R.id.txtNombre);
        viewDireccion=(TextView)view.findViewById(R.id.txtDireccion);
        viewEmail=(TextView) view.findViewById(R.id.txtEmail);
        viewTelefono=(TextView)view.findViewById(R.id.txtTelefono);
        imgContacto= (ImageView) view.findViewById(R.id.imgContacto);
        checkBox= (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.toggle();
            }
        });
    }

    public Contactos getContacto(){
        return contactoActual;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(listener!=null){
            listener.fragmentChecked(this, isChecked);
        }
    }

    //interfas
    public static interface FragmentCheckedListener{
        public  void fragmentChecked(ContactoFragment cFrag, boolean isChecked);

    }
}
