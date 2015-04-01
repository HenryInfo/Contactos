package com.youtube.contactos.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.j256.ormlite.field.DatabaseField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Created by henryyerrybravosanchez on 3/30/15.
 */
public class JSONBean implements PropertyChangeListener, Serializable {

    @JsonProperty
    @DatabaseField
    protected String md5;
    private int serverId;

    public JSONBean() {
        support.addPropertyChangeListener(this);
    }

    //<editor-fold desc="PROPERTY CHANGE SUPPORT">
    @JsonIgnore
    protected PropertyChangeSupport support=new PropertyChangeSupport(this );

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }


    //</editor-fold>

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        procesarHashMD5();
    }

    private void procesarHashMD5() {
        HashFunction hf= Hashing.md5();
        HashCode hc=hf.hashInt(hashCode());
        md5= hc.toString();
    }

    public String getMd5() {
        return md5;
    }

    public int getServerId() {
        return serverId;
    }

}
