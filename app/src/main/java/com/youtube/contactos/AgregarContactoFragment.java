package com.youtube.contactos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.youtube.contactos.util.ConcatReceiver;
import com.youtube.contactos.util.Contactos;
import com.youtube.contactos.util.TxtChangeListener;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class AgregarContactoFragment extends Fragment implements View.OnClickListener {

    private EditText txtNombre, txtTelefono, txtDireccion, txtCorreo;
    private Button btnAgregar;
    private ImageView image;
    private int code_res=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.crear_fragmento_contacto, container, false);
        iniciarComponentes(rootView);
        return rootView;
    }

    private void iniciarComponentes(final View view) {
        txtNombre=(EditText)view.findViewById(R.id.txtName);
        txtDireccion=(EditText)view.findViewById(R.id.txtDireccion);
        txtCorreo=(EditText)view.findViewById(R.id.txtCorreo);
        txtTelefono=(EditText)view.findViewById(R.id.txtNumero);
        image=(ImageView)view.findViewById(R.id.imageView);
        image .setOnClickListener(this);

        txtNombre.addTextChangedListener(new TxtChangeListener(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtTelefono.addTextChangedListener(new TxtChangeListener(){
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        btnAgregar.setEnabled(!s.toString().trim().isEmpty());
                    }
                });
            }
        });

        btnAgregar=(Button)view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==btnAgregar){
            agregarContactos(
                    txtNombre.getText().toString(),
                    txtTelefono.getText().toString(),
                    txtCorreo.getText().toString(),
                    txtDireccion.getText().toString(),
                    String.valueOf(image.getTag())
            );
            String mensaje= String.format("%s A sido agregado a la lista",txtNombre.getText());
            Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();
            btnAgregar.setEnabled(false);
            limpiarCampos();
        }else {
            Intent intent= null;
            if(Build.VERSION.SDK_INT<19){
                //ANDROID JELLYBEAN
                intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, code_res);
            }
            //Android KITKAT 4.4
            intent= new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, code_res);

        }
    }
    private void agregarContactos(String s, String s1, String s2, String s3, String image) {
        Contactos contacto= new Contactos(s, s1, s3, s2, image);
        Intent intent= new Intent("lc");
        intent.putExtra("operacion", ConcatReceiver.CONTACTO_AGREGADO);
        intent.putExtra("datos", contacto);
        getActivity().sendBroadcast(intent );
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtCorreo.getText().clear();
        txtTelefono.getText().clear();
        txtDireccion.getText().clear();
        image.setImageResource(R.drawable.user_icon);
        txtNombre.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode==code_res){
            image.setImageURI(data.getData());
            image.setTag(data.getData());
        }
    }
}