package com.youtube.contactos.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by henryyerrybravosanchez on 3/12/15.
 */

@DatabaseTable
public class Contactos extends JSONBean implements Serializable  {
    @JsonProperty
    @DatabaseField
    private  Integer severId;

    @JsonProperty("androidId")
    @DatabaseField(generatedId = true)
    private  int id;

    @JsonProperty
    @DatabaseField(index = true, canBeNull = false)
    private String nombre;

    @JsonProperty
    @DatabaseField
    private String telefono;

    @JsonProperty
    @DatabaseField
    private String direccion;

    @JsonProperty
    @DatabaseField
    private String correo;

    @JsonProperty
    @DatabaseField
    private String imageUri;


    @JsonProperty
    @DatabaseField
    private String propietario;
    public Contactos(){

    }

    public Contactos(int id, String nombre, String telefono, String direccion, String correo, String imageUri, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.imageUri = imageUri;
        this.propietario = propietario;
    }
    public Contactos(String nombre, String telefono, String direccion, String correo, String imageUri, String propietario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.imageUri = imageUri;
        this.propietario = propietario;
    }

    //<editor-fold desc="METHOD GETERS">
    public int getId() {
        return id;
    }
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

    public String getPropietario() {
        return propietario;
    }

    //</editor-fold>


    public void setId(int id) {
        int oldInt =this.id;
        this.id = id;
        support.firePropertyChange("id", oldInt, id);
    }

    public void setNombre(String nombre) {
        String oldVal=this.nombre;
        this.nombre = nombre;
        support.firePropertyChange("nombre", oldVal, nombre);
    }

    public void setTelefono(String telefono) {
        String oldVal=this.telefono;
        this.telefono = telefono;
        support.firePropertyChange("telefono", oldVal, telefono);
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
        String oldVal=this.direccion;
        support.firePropertyChange("direccion", oldVal, direccion);
    }

    public void setCorreo(String correo) {
        this.correo = correo;
        String oldVal=this.correo;
        support.firePropertyChange("correo", oldVal, correo);
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
        String oldVal=this.imageUri;
        support.firePropertyChange("imageUri", oldVal, imageUri);
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
        String oldVal=this.propietario;
        support.firePropertyChange("propietario", oldVal, propietario);

    }

    //<editor-fold desc="HASH CODE">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contactos)) return false;

        Contactos contactos = (Contactos) o;

        if (id != contactos.id) return false;
        if (correo != null ? !correo.equals(contactos.correo) : contactos.correo != null)
            return false;
        if (direccion != null ? !direccion.equals(contactos.direccion) : contactos.direccion != null)
            return false;
        if (imageUri != null ? !imageUri.equals(contactos.imageUri) : contactos.imageUri != null)
            return false;
        if (nombre != null ? !nombre.equals(contactos.nombre) : contactos.nombre != null)
            return false;
        if (propietario != null ? !propietario.equals(contactos.propietario) : contactos.propietario != null)
            return false;
        if (telefono != null ? !telefono.equals(contactos.telefono) : contactos.telefono != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (imageUri != null ? imageUri.hashCode() : 0);
        result = 31 * result + (propietario != null ? propietario.hashCode() : 0);
        return result;
    }
    //</editor-fold>
}
