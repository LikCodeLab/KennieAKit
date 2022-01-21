package com.kennie.database.example;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

public class DataBaseEncrypt {

    private static volatile DataBaseEncrypt nInstance;
    private Boolean isOpen = true;

    public DataBaseEncrypt() {
    }

    public static DataBaseEncrypt getInstences() {
        if (nInstance == null) {
            synchronized (DataBaseEncrypt.class) {
                if (nInstance == null) {
                    nInstance = new DataBaseEncrypt();
                }
            }
        }
        return nInstance;
    }

    /**
     * 如果已有未加密的数据库旧表 先加密已有未加密数据库
     *
     * @param context
     * @param passphrase
     */
    public void encrypt(Context context, String dbName, String passphrase) {
        File file = context.getDatabasePath(dbName);
        if (file.exists() && isOpen) {
            try {
                //创建临时数据库文件
                File newFile = File.createTempFile("sqlcipherutils", "tmp", context.getCacheDir());
                //对没有加密的数据库进行加密操作
                SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), "", null, SQLiteDatabase.OPEN_READWRITE);
                db.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';", newFile.getAbsolutePath(), passphrase));
                db.rawExecSQL("SELECT sqlcipher_export('encrypted')");
                db.rawExecSQL("DETACH DATABASE encrypted;");
                int version = db.getVersion();
                db.close();

                //对已加密过的数据库设置版本号
                db = SQLiteDatabase.openDatabase(newFile.getAbsolutePath(), passphrase, null, SQLiteDatabase.OPEN_READWRITE);
                db.setVersion(version);
                db.close();
                file.delete();

                //对加密后的数据库进行重新命名
                newFile.renameTo(file);
                isOpen = false;
            } catch (Exception e) {
                isOpen = false;
            }
        }
    }

    /**
     * 如果对已有数据进行加密，需要先对原有数据库进行加密操作
     * @return
     */
//    public synchronized Database getWritableDatabase() {
//        try {
//            return devOpenHelper.getEncryptedWritableDb(DATABASE_SQLCIPHER_PASSWORD);
//        } catch (Exception e) {
//            e.printStackTrace();
//            //尝试加密后再打开
//            DataBaseEncrypt.getInstences().encrypt(MyApp.getContext(), DATABASE_NAME, DATABASE_SQLCIPHER_PASSWORD);
//            return devOpenHelper.getEncryptedWritableDb(DATABASE_SQLCIPHER_PASSWORD);
//        }
//    }
//
//    // 初始化数据库信息
//    devOpenHelper = new MyOpenHelper(MyApp.getContext(), DATABASE_NAME, null);
//    //mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
//    //数据库加密
//    mDaoMaster = new DaoMaster(getWritableDatabase());
}
