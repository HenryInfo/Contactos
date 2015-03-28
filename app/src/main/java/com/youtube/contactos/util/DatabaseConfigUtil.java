package com.youtube.contactos.util;


import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.io.IOException;
import java.sql.SQLException;

import static com.j256.ormlite.android.apptools.OrmLiteConfigUtil.writeConfigFile;

/**
 * Created by henryyerrybravosanchez on 3/14/15.
 */
public class DatabaseConfigUtil extends OrmLiteBaseActivity {

    public static void main(String[] arg) throws IOException, SQLException{
        writeConfigFile("ormlite_config.txt");
    }
}
