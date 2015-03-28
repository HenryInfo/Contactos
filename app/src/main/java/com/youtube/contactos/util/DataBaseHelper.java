package com.youtube.contactos.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.youtube.contactos.R;

import java.sql.SQLException;

/**
 * Created by henryyerrybravosanchez on 3/14/15.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME= "agenda.bd";
    private static final int DATABASE_VERSION=1;
    private Dao<Contactos, Integer> contactoDao=null;
    private RuntimeExceptionDao<Contactos, Integer> contactoExceptionDao = null;

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        Log.i(DataBaseHelper.class.getSimpleName(),"onCreate");
        try {
            TableUtils.createTable(connectionSource, Contactos.class);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getSimpleName(), "Imposible crear la bd");
            throw new  RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        Log.i(DataBaseHelper.class.getSimpleName(),"onUpdate()");
        try {
            TableUtils.dropTable(connectionSource, Contactos.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getSimpleName(), "Imposible eliminar la bd");
            throw new  RuntimeException(e);
        }
    }

    //<editor-fold desc="GETTERS DAO AND RUNTIMEEXCEPTION">
    public Dao<Contactos, Integer> getContactoDao (Class<Contactos> contactosClass) throws SQLException {
        if(contactoDao==null) contactoDao=getDao(Contactos.class);
        return contactoDao;
    }

    public RuntimeExceptionDao<Contactos, Integer> getRuntimeExceptionDao() {
        if(contactoExceptionDao ==null) contactoExceptionDao = getRuntimeExceptionDao(Contactos.class);
        return contactoExceptionDao;
    }
    //</editor-fold>

    @Override
    public void close() {
        super.close();
        contactoDao=null;
        contactoExceptionDao =null;
    }
}
