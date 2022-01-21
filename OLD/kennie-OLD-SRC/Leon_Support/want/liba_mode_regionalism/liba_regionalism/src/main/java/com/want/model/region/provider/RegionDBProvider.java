package com.want.model.region.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/6/13<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class RegionDBProvider extends SQLiteOpenHelper {
    public static final String DB_NAME = "region.db";

    public static final class Entry {
        public static final String TABLE = "REGIONS";

        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_NAME = "NAME";
    }

    private static final int VERSION = 1;


    public RegionDBProvider(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
