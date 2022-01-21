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

package com.lkkit.region;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.lkkit.region.bean.Region;
import com.lkkit.region.provider.RegionDBProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.lkkit.region.provider.RegionDBProvider.DB_NAME;
import static com.lkkit.region.provider.RegionDBProvider.Entry;


/**
 * @TITLE: RegionHelper
 * @PACKAGE com.jackylee.region
 * @DESCRIPTION: ${TODO}(用一句话描述该文件做什么)
 * @AUTHOR: JackyLee
 * @DATE: 2019/04/05 14:51
 * @VERSION V 0.1
 */
public class RegionHelper {
    private static final String[] COLUMNS = new String[]{Entry.COLUMN_ID, Entry.COLUMN_NAME};

    private static final String DB_PATH = "databases";

    private static RegionHelper INSTANCE;
    private WeakReference<Context> mContextReference;
    private String mDBName = DB_NAME;
    private String mDBPath;

    private RegionDBProvider mDbProvider;


    private RegionHelper(Context context) {
        //no instance

        mContextReference = new WeakReference<>(context);
        mDbProvider = new RegionDBProvider(mContextReference.get());
//        mDbpath = context.getDatabasePath(DB_NAME).getPath();
        mDBPath = context.getApplicationInfo().dataDir + File.separator + DB_PATH;
    }

    private SQLiteDatabase getDatabase() {
        return mDbProvider.getWritableDatabase();
    }

    public static synchronized void init(Context context) {
        if (null != INSTANCE) {
            return;
        }
        INSTANCE = new RegionHelper(context);
        // 1) copy db file to db dir
        if (!INSTANCE.checkDatabase()) {
            INSTANCE.copyDatabase();
        }
    }

    private boolean checkDatabase() {
        File file = new File(mDBPath + File.separator + mDBName);
        if (!file.exists()) {
            return false;
        }

        SQLiteDatabase db = null;
        try {
            db =
                    SQLiteDatabase.openDatabase(file.getPath(),
                            null,
                            SQLiteDatabase.NO_LOCALIZED_COLLATORS |
                                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            e.printStackTrace();
            // ignore
        } finally {
            if (null != db) {
                db.close();
            }
        }

        return null != db;
    }

    private void copyDatabase() {
        InputStream ins = null;
        OutputStream ops = null;
        try {
            ins = mContextReference.get().getAssets().open(RegionDBProvider.DB_NAME);
            File file = new File(mDBPath);
            if (!file.exists()) {
                file.mkdir();
            }

            ops = new FileOutputStream(file.getPath() + File.separator + mDBName);

            byte[] byts = new byte[1024];
            int length;
            while ((length = ins.read(byts)) > 0) {
                ops.write(byts, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ops) {
                try {
                    ops.flush();
                    ops.close();
                } catch (IOException e) {
                    // ignore
                }
            }

            if (null != ins) {
                try {
                    ins.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    public static RegionHelper getInstance() {
        if (null == INSTANCE) {
            throw new RuntimeException("You must call init() method before getInstance().");
        }
        return INSTANCE;
    }

    /**
     * Get all the provinces
     *
     * @return
     */
    public List<Region> getProvinces() {
        final List<Region> regions = new ArrayList<>();
        final SQLiteDatabase db = getDatabase();

        Cursor cursor = db.query(Entry.TABLE,
                COLUMNS,
                Entry.COLUMN_ID + " LIKE ?",
                new String[]{"%" + "0000"},
                null,
                null,
                null);
        int id;
        String name;
        Region region;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(Entry.COLUMN_ID));
            name = cursor.getString(cursor.getColumnIndex(Entry.COLUMN_NAME));

            region = new Region();
            region.id = id;
            region.name = name;
            regions.add(region);
        }

        cursor.close();

        return regions;
    }

    /**
     * Get the province by the special region. The region can be city or district.
     *
     * @param region
     * @return
     */
    public Region getProvince(Region region) {
        final SQLiteDatabase db = getDatabase();

        final int id = region.id;

        Cursor cursor = db.query(Entry.TABLE,
                COLUMNS,
                Entry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        region = new Region();
        while (cursor.moveToNext()) {
            region.id = getId(cursor);
            region.name = getName(cursor);
        }
        cursor.close();

        return region;
    }

    /**
     * Get all the citys of this province specialed by region.
     *
     * @param region
     * @return
     */
    public List<Region> getCitys(Region region) {
        final List<Region> datas = new ArrayList<>();
        final SQLiteDatabase db = getDatabase();
        final String id = String.valueOf(region.id);
        Cursor cursor = db.query(Entry.TABLE,
                COLUMNS,
                Entry.COLUMN_ID + " LIKE ?",
                new String[]{id.substring(0, 2) + "%" + "00"},
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            region = new Region();
            region.id = getId(cursor);
            region.name = getName(cursor);
            datas.add(region);
        }
        cursor.close();

        if (datas.size() > 1) {
            datas.remove(0);
        }
        return datas;
    }

    /**
     * Get the city by special region. The region should be the district.
     *
     * @param region
     * @return
     */
    public Region getCity(Region region) {
        final SQLiteDatabase db = getDatabase();
        final String id = String.valueOf(region.id);

        Cursor cursor = db.query(Entry.TABLE,
                COLUMNS,
                Entry.COLUMN_ID + "=?",
                new String[]{id.substring(0, 3) + "00"},
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            region = new Region();
            region.id = getId(cursor);
            region.name = getName(cursor);
        }
        cursor.close();

        return region;
    }

    /**
     * Get all the districts of this city specialed by region.
     *
     * @param region
     * @return
     */
    public List<Region> getDistricts(Region region) {
        final List<Region> datas = new ArrayList<>();
        final SQLiteDatabase db = getDatabase();
        final String id = String.valueOf(region.id);

        Cursor cursor = db.query(Entry.TABLE,
                COLUMNS,
                Entry.COLUMN_ID + " LIKE ?",
                new String[]{id.substring(0, 4) + "%"},
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            region = new Region();
            region.id = getId(cursor);
            region.name = getName(cursor);
            datas.add(region);
        }
        cursor.close();

        final int count = datas.size();
        // 1) remove invalid data
        if (count > 1) {
            datas.remove(0);
            // 2) research
        } else if (count == 1) {
            cursor = db.query(Entry.TABLE,
                    COLUMNS,
                    Entry.COLUMN_ID + " LIKE ?",
                    new String[]{id.substring(0, 3) + "%"},
                    null,
                    null,
                    null);
            datas.clear();
            while (cursor.moveToNext()) {
                region = new Region();
                region.id = getId(cursor);
                region.name = getName(cursor);
                datas.add(region);
            }
            cursor.close();
            if (datas.size() > 1) {
                datas.remove(0);
            }
        }


        return datas;
    }

    /**
     * Get regions like the special name.
     *
     * @param name
     * @return
     */
    public List<Region> getRegionByName(String name) {
        final List<Region> datas = new ArrayList<>();
        final SQLiteDatabase db = getDatabase();
        Cursor cursor = db.query(Entry.TABLE,
                COLUMNS,
                Entry.COLUMN_NAME + "LIKE ?",
                new String[]{"%" + name + "%"},
                null,
                null,
                null);
        Region region;
        while (cursor.moveToNext()) {
            region = new Region();
            region.id = getId(cursor);
            region.name = getName(cursor);
            datas.add(region);
        }
        cursor.close();

        return datas;
    }

    private int getId(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(Entry.COLUMN_ID));
    }

    private String getName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(Entry.COLUMN_NAME));
    }
}
