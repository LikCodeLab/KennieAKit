//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//  ┃　　　┃   神兽保佑　　　　　　　　
//  ┃　　　┃   代码无BUG！
//  ┃　　　┗━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━┳┓┏┛
//    ┃┫┫　┃┫┫
//    ┗┻┛　┗┻┛

package com.lkkit.region.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @TITLE: RegionDBProvider
 * @PACKAGE com.jackylee.region.bean
 * @DESCRIPTION: ${TODO}(用一句话描述该文件做什么)
 * @AUTHOR: JackyLee
 * @DATE: 2019/04/05 14:51
 * @VERSION V 0.1
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

