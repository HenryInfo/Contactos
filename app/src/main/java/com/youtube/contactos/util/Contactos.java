package com.youtube.contactos.util;

import java.io.Serializable;

/**
 * Created by henryyerrybravosanchez on 3/12/15.
 */
public class Contactos implements Serializable{
    private String nombre, telefono, direccion, correo;
    private String imageUri;


    public Contactos(String nombre, String telefono, String direccion, String correo, String image) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.imageUri=image;
    }



    //<editor-fold desc="METHOD GETERS">
    public String getNombre() {
        return nombre;
    }
    public String getImageUri() {
        return imageUri;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }
    //</editor-fold>

    //<editor-fold desc="METHODS SETTERS">
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contactos)) return false;

        Contactos contactos = (Contactos) o;

        if (!correo.equals(contactos.correo)) return false;
        if (!direccion.equals(contactos.direccion)) return false;
        if (!imageUri.equals(contactos.imageUri)) return false;
        if (!nombre.equals(contactos.nombre)) return false;
        if (!telefono.equals(contactos.telefono)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + telefono.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + correo.hashCode();
        result = 31 * result + imageUri.hashCode();
        return result;
    }
}
